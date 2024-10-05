/* radare2 - LGPL - Copyright 2017-2023 - condret, pancake, alvaro */

#include <r_io.h>

// shall be used by plugins for creating descs
R_API RIODesc* r_io_desc_new(RIO* io, RIOPlugin* plugin, const char* uri, int perm, int mode, void* data) {
	R_RETURN_VAL_IF_FAIL (io && plugin && uri, NULL);
	ut32 fd32 = 0;
	if (io->files) {
		if (!r_id_pool_grab_id (io->files->pool, &fd32)) {
			return NULL;
		}
	}
	RIODesc* desc = R_NEW0 (RIODesc);
	if (desc) {
		desc->fd = fd32;
		desc->io = io;
		desc->plugin = plugin;
		desc->data = data;
		desc->perm = perm & (R_PERM_RWX | R_PERM_CREAT);
		//because the uri-arg may live on the stack
		desc->uri = strdup (uri);
		// XXX used for io redirects desc->name = strdup (uri);
	}
	return desc;
}

R_API void r_io_desc_free(RIODesc* desc) {
	if (desc) {
		free (desc->uri);
		free (desc->referer);
		free (desc->name);
		r_io_desc_cache_fini (desc);
		if (desc->io && desc->io->files) {
			r_id_storage_delete (desc->io->files, desc->fd);
		}
//		free (desc->plugin);
		free (desc);
	}
}

R_API bool r_io_desc_add(RIO* io, RIODesc* desc) {
	R_RETURN_VAL_IF_FAIL (io && desc && desc->io, false);
	if (!r_id_storage_set (io->files, desc, desc->fd)) {
		// TODO: use assert here
		R_LOG_ERROR ("Wrong API usage. fd %d wasnt generated by this IO instance", desc->fd);
		r_sys_backtrace ();
		return false;
	}
	return true;
}

// can we pass this a riodesc and check if it belongs to the desc->io ?
R_API bool r_io_desc_del(RIO* io, int fd) {
	R_RETURN_VAL_IF_FAIL (io && io->files, false);
	RIODesc* desc = r_id_storage_get (io->files, fd);
	if (desc == io->desc) {
		io->desc = NULL;
	}
	// remove all related maps
	r_io_map_del_for_fd (io, desc->fd);
	r_io_desc_free (desc);
	return true;
}

R_API RIODesc* r_io_desc_get(RIO* io, int fd) {
	R_RETURN_VAL_IF_FAIL (io && io->files, NULL);
	return (RIODesc*) r_id_storage_get (io->files, fd);
}

R_API RIODesc *r_io_desc_get_byuri(RIO *io, const char *uri) {
	R_RETURN_VAL_IF_FAIL (io && io->files, NULL);
	RIODesc *d = r_io_desc_get_lowest (io);
	while (d) {
		if (!strcmp (d->uri, uri)) {
			return d;
		}
		d = r_io_desc_get_next (io, d);
	}
	return NULL;
}

R_API RIODesc *r_io_desc_get_next(RIO *io, RIODesc *desc) {
	R_RETURN_VAL_IF_FAIL (desc && io && io->files, NULL);
	const int next_fd = r_io_fd_get_next (io, desc->fd);
	if (next_fd == -1) {
		return NULL;
	}
	return (RIODesc*) r_id_storage_get (io->files, next_fd);
}

R_API RIODesc *r_io_desc_get_prev(RIO *io, RIODesc *desc) {
	R_RETURN_VAL_IF_FAIL (desc && io && io->files, NULL);
	const int prev_fd = r_io_fd_get_prev (io, desc->fd);
	if (prev_fd == -1) {
		return NULL;
	}
	return (RIODesc*) r_id_storage_get (io->files, prev_fd);
}

R_API RIODesc *r_io_desc_get_highest(RIO *io) {
	int fd = r_io_fd_get_highest (io);
	if (fd == -1) {
		return NULL;
	}
	return r_io_desc_get (io, fd);
}

R_API RIODesc *r_io_desc_get_lowest(RIO *io) {
	int fd = r_io_fd_get_lowest (io);
	if (fd == -1) {
		return NULL;
	}
	return r_io_desc_get (io, fd);
}

R_API RIODesc *r_io_desc_open(RIO *io, const char *uri, int perm, int mode) {
	R_RETURN_VAL_IF_FAIL (io && uri, NULL);
	RIOPlugin *plugin = r_io_plugin_resolve (io, uri, 0);
	if (!plugin || !plugin->open) {
		return NULL;
	}
	RIODesc *desc = plugin->open (io, uri, perm, mode);
	if (!desc) {
		return NULL;
	}
	// for none static callbacks, those that cannot use r_io_desc_new
	if (!desc->name) {
		desc->name = strdup (uri);
	}
	if (!desc->uri) {
		desc->uri = strdup (uri);
	}
	if (!desc->plugin) {
		desc->plugin = plugin;
	}
	if (!r_io_desc_add (io, desc)) {
		r_io_desc_free (desc);
		return NULL;
	}
	return desc;
}

R_API RIODesc *r_io_desc_open_plugin(RIO *io, RIOPlugin *plugin, const char *uri, int perm, int mode) {
	R_RETURN_VAL_IF_FAIL (io && io->files && uri && plugin, NULL);
	if (!plugin->open || !plugin->check || !plugin->check (io, uri, false)) {
		return NULL;
	}
	RIODesc *desc = plugin->open (io, uri, perm, mode);
	if (!desc) {
		return NULL;
	}
	// for none static callbacks, those that cannot use r_io_desc_new
	if (!desc->plugin) {
		desc->plugin = plugin;
	}
	if (!desc->uri) {
		desc->uri = strdup (uri);
	}
	if (!desc->name) {
		desc->name = strdup (uri);
	}
	if (!r_io_desc_add (io, desc)) {
		r_io_desc_free (desc);
		return NULL;
	}
	return desc;
}

R_API bool r_io_desc_close(RIODesc *desc) {
	if (!desc || !desc->io || !desc->plugin) {
		return false;
	}
	RIO *io = desc->io;
	if (desc->plugin->close && !desc->plugin->close (desc)) {
		return false;
	}
	// remove entry from idstorage and free the desc struct
	r_io_desc_del (io, desc->fd);
	return true;
}

//returns length of written bytes
R_API int r_io_desc_write(RIODesc *desc, const ut8* buf, int len) {
	R_RETURN_VAL_IF_FAIL (desc && buf, -1);
	if (len < 0) {
		return -1;
	}
	// check pointers and pcache
	if (desc->io && (desc->io->p_cache & 2)) {
		return r_io_desc_cache_write (desc,
				r_io_desc_seek (desc, 0LL, R_IO_SEEK_CUR), buf, len);
	}
	return r_io_plugin_write (desc, buf, len);
}

// returns length of read bytes
R_API int r_io_desc_read(RIODesc *desc, ut8 *buf, int len) {
	R_RETURN_VAL_IF_FAIL (desc && buf, -1);
	// check pointers and permissions
	if (!(desc->perm & R_PERM_R)) {
		return -1;
	}
	ut64 seek = r_io_desc_seek (desc, 0LL, R_IO_SEEK_CUR);
	int ret = r_io_plugin_read (desc, buf, len);
	if ((ret > 0) && desc->io && (desc->io->p_cache & 1)) {
		ret = r_io_desc_cache_read (desc, seek, buf, ret);
	}
	return ret;
}

R_API ut64 r_io_desc_seek(RIODesc* desc, ut64 offset, int whence) {
	if (!desc || !desc->plugin || !desc->plugin->seek) {
		return (ut64) -1;
	}
	return desc->plugin->seek (desc->io, desc, offset, whence);
}

R_API ut64 r_io_desc_size(RIODesc* desc) {
	if (!desc || !desc->plugin || !desc->plugin->seek) {
		return 0LL;
	}
	ut64 off = r_io_desc_seek (desc, 0LL, R_IO_SEEK_CUR);
	ut64 ret = r_io_desc_seek (desc, 0LL, R_IO_SEEK_END);
	// what to do if that seek fails?
	r_io_desc_seek (desc, off, R_IO_SEEK_SET);
	return ret;
}

typedef struct desc_map_resize_t {
	RIO *io;
	RQueue *del;
	ut64 osize;
	ut64 size;
	int fd;
} DescMapResize;

static bool _resize_affected_maps (void *user, void *data, ut32 id) {
	DescMapResize *dmr = (DescMapResize *)user;
	RIOMap *map = (RIOMap *)data;
	if (map->fd == dmr->fd) {
		if (map->delta >= dmr->size) {
			r_queue_enqueue (dmr->del, map);
			return true;
		}
		if (map->tie_flags & R_IO_MAP_TIE_FLG_BACK) {
			const double ratio = ((double)dmr->size) / ((double)dmr->osize);
			io_map_resize (dmr->io, id, (ut64)(((double)r_io_map_size (map)) * ratio));
		} else if ((map->delta + r_io_map_size (map)) > dmr->size) {
			io_map_resize (dmr->io, id, dmr->size - map->delta);
		}
	}
	return true;
}

R_API bool r_io_desc_resize(RIODesc *desc, ut64 newsize) {
	if (desc && desc->plugin && desc->plugin->resize && desc->plugin->seek) {
		const ut64 osize = r_io_desc_size (desc);
		if (osize == newsize) {
			return true;
		}
		if (!desc->plugin->resize (desc->io, desc, newsize)) {
			return false;
		}
		if (osize > newsize && desc->io && desc->io->p_cache) {
			r_io_desc_cache_cleanup (desc);
		}
		DescMapResize dmr = {desc->io, r_queue_new (1), osize, newsize, desc->fd};
		if (desc->io->maps) {
			r_id_storage_foreach (desc->io->maps, _resize_affected_maps, &dmr);
			while (!r_queue_is_empty (dmr.del)) {
				r_io_map_del (desc->io, ((RIOMap *)r_queue_dequeue (dmr.del))->id);
			}
		}
		r_queue_free (dmr.del);
		return true;
	}
	return false;
}

R_API char *r_io_desc_system(RIODesc *desc, const char *cmd) {
	if (desc && desc->plugin && desc->plugin->system) {
		return desc->plugin->system (desc->io, desc, cmd);
	}
	return NULL;
}

R_API bool r_io_desc_is_blockdevice(RIODesc *desc) {
	if (!desc || !desc->plugin || !desc->plugin->is_blockdevice) {
		return false;
	}
	return desc->plugin->is_blockdevice (desc);
}

R_API bool r_io_desc_is_chardevice(RIODesc *desc) {
	if (!desc || !desc->plugin || !desc->plugin->is_chardevice) {
		return false;
	}
	return desc->plugin->is_chardevice (desc);
}

R_API bool r_io_desc_exchange(RIO* io, int fd, int fdx) {
	RIODesc* desc, * descx;
	if (!(desc = r_io_desc_get (io, fd)) || !(descx = r_io_desc_get (io, fdx))) {
		return false;
	}
	desc->fd = fdx;
	descx->fd = fd;
	r_id_storage_set (io->files, desc,  fdx);
	r_id_storage_set (io->files, descx, fd);
	if (io->p_cache) {
		HtUP* cache = desc->cache;
		desc->cache = descx->cache;
		descx->cache = cache;
		r_io_desc_cache_cleanup (desc);
		r_io_desc_cache_cleanup (descx);
	}
	ut32 map_id;
	if (r_id_storage_get_lowest (io->maps, &map_id)) {
		do {
			RIOMap *map = r_id_storage_get (io->maps, map_id);
			if (map->fd == fdx) {
				map->perm &= (desc->perm | R_PERM_X);
			} else if (map->fd == fd) {
				map->perm &= (descx->perm | R_PERM_X);
			}
		} while (r_id_storage_get_next (io->maps, &map_id));
	}
	return true;
}

R_API bool r_io_desc_is_dbg(RIODesc *desc) {
	if (desc && desc->plugin) {
		return desc->plugin->isdbg;
	}
	return false;
}

R_API int r_io_desc_get_pid(RIODesc *desc) {
	//-1 and -2 are reserved
	if (!desc) {
		return -3;
	}
	if (!desc->plugin) {
		return -4;
	}
	if (!desc->plugin->isdbg) {
		return -5;
	}
	if (!desc->plugin->getpid) {
		return -6;
	}
	return desc->plugin->getpid (desc);
}

R_API int r_io_desc_get_tid(RIODesc *desc) {
	//-1 and -2 are reserved
	if (!desc) {
		return -3;
	}
	if (!desc->plugin) {
		return -4;
	}
	if (!desc->plugin->isdbg) {
		return -5;
	}
	if (!desc->plugin->gettid) {
		return -6;
	}
	return desc->plugin->gettid (desc);
}

R_API bool r_io_desc_get_base(RIODesc *desc, ut64 *base) {
	if (!base || !desc || !desc->plugin || !desc->plugin->isdbg || !desc->plugin->getbase) {
		return false;
	}
	return desc->plugin->getbase (desc, base);
}

R_API int r_io_desc_read_at(RIODesc *desc, ut64 addr, ut8 *buf, int len) {
	if (desc && buf && (r_io_desc_seek (desc, addr, R_IO_SEEK_SET) == addr)) {
		return r_io_desc_read (desc, buf, len);
	}
	return 0;
}

R_API int r_io_desc_write_at(RIODesc *desc, ut64 addr, const ut8 *buf, int len) {
	if (desc && buf && (r_io_desc_seek (desc, addr, R_IO_SEEK_SET) == addr)) {
		return r_io_desc_write (desc, buf, len);
	}
	return 0;
}

R_API bool r_io_desc_extend(RIODesc *desc, ut64 size) {
	if (desc && desc->plugin && desc->plugin->extend) {
		return desc->plugin->extend (desc->io, desc, size);
	}
	return 0;
}

/* lifecycle */

// TODO: move into io.c : r_io_init
R_IPI bool r_io_desc_init(RIO* io) {
	R_RETURN_VAL_IF_FAIL (io, false);
	r_io_desc_fini (io);
	// TODO: it leaks if called twice
	//fd is signed
	io->files = r_id_storage_new (3, 0x80000000);
	if (!io->files) {
		return false;
	}
	return true;
}

static bool desc_fini_cb(void* user, void* data, ut32 id) {
	RIODesc* desc = (RIODesc*) data;
	if (desc && desc->plugin && desc->plugin->close) {
		desc->plugin->close (desc);
	}
	r_io_desc_free (desc);
	return true;
}

//closes all descs and frees all descs and io->files
R_IPI void r_io_desc_fini(RIO* io) {
	R_RETURN_IF_FAIL (io);
	if (io->files) {
		r_id_storage_foreach (io->files, desc_fini_cb, io);
		r_id_storage_free (io->files);
		io->files = NULL;
	}
	//no map-cleanup here, to keep it modular useable
	io->desc = NULL;
}
