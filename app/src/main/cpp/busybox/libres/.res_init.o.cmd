cmd_libres/res_init.o := x86_64-linux-android-gcc -Wp,-MD,libres/.res_init.o.d  -std=gnu99 -Iinclude -Ilibbb  -include include/autoconf.h -D_GNU_SOURCE -DNDEBUG -D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 -DBB_VER='"1.36.0"' -Wall -Wshadow -Wwrite-strings -Wundef -Wstrict-prototypes -Wunused -Wunused-parameter -Wunused-function -Wunused-value -Wmissing-prototypes -Wmissing-declarations -Wno-format-security -Wdeclaration-after-statement -Wold-style-definition -finline-limit=0 -fno-builtin-strlen -fomit-frame-pointer -ffunction-sections -fdata-sections -funsigned-char -static-libgcc -falign-functions=1 -falign-jumps=1 -falign-labels=1 -falign-loops=1 -fno-unwind-tables -fno-asynchronous-unwind-tables -fno-builtin-printf -g -Oz --sysroot="/home/pangbai/tmp/ndk/x86_64/sysroot/"    -DKBUILD_BASENAME='"res_init"'  -DKBUILD_MODNAME='"res_init"' -c -o libres/res_init.o libres/res_init.c

deps_libres/res_init.o := \
  libres/res_init.c \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/resolv.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/param.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/endian.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/endian.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/cdefs.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/versioning.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/api-level.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/get_device_api_level_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/ndk-version.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stdint.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/stdint.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/wchar_limits.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stddef.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/limits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/limits.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/float.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/limits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/posix_limits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/param.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/param.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/param.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/int-ll64.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/bitsperlong.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/bitsperlong.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/posix_types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/stddef.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/compiler_types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/compiler.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/posix_types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/posix_types_64.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/posix_types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/pthread_types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/fcntl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/fcntl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/sockios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/sockios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/sockios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/uio.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/sa_family_t.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/stdio.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stdarg.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/seek_constants.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/arpa/nameser.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/arpa/nameser_compat.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/netinet/in.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/netinet/in6.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/in6.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/libc-compat.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/in.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/ip_msfilter.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/in_addr.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/ip_mreq_source.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/byteorder.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/byteorder/little_endian.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/swab.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/swab.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/ipv6.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/netdb.h \
  libres/dietdns.h \

libres/res_init.o: $(deps_libres/res_init.o)

$(deps_libres/res_init.o):