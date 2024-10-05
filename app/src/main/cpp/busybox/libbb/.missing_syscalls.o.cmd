cmd_libbb/missing_syscalls.o := x86_64-linux-android-gcc -Wp,-MD,libbb/.missing_syscalls.o.d  -std=gnu99 -Iinclude -Ilibbb  -include include/autoconf.h -D_GNU_SOURCE -DNDEBUG -D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 -DBB_VER='"1.36.0"' -Wall -Wshadow -Wwrite-strings -Wundef -Wstrict-prototypes -Wunused -Wunused-parameter -Wunused-function -Wunused-value -Wmissing-prototypes -Wmissing-declarations -Wno-format-security -Wdeclaration-after-statement -Wold-style-definition -finline-limit=0 -fno-builtin-strlen -fomit-frame-pointer -ffunction-sections -fdata-sections -funsigned-char -static-libgcc -falign-functions=1 -falign-jumps=1 -falign-labels=1 -falign-loops=1 -fno-unwind-tables -fno-asynchronous-unwind-tables -fno-builtin-printf -g -Oz --sysroot="/home/pangbai/tmp/ndk/x86_64/sysroot/"    -DKBUILD_BASENAME='"missing_syscalls"'  -DKBUILD_MODNAME='"missing_syscalls"' -c -o libbb/missing_syscalls.o libbb/missing_syscalls.c

deps_libbb/missing_syscalls.o := \
  libbb/missing_syscalls.c \
  include/libbb.h \
    $(wildcard include/config/feature/shadowpasswds.h) \
    $(wildcard include/config/use/bb/shadow.h) \
    $(wildcard include/config/selinux.h) \
    $(wildcard include/config/feature/utmp.h) \
    $(wildcard include/config/locale/support.h) \
    $(wildcard include/config/use/bb/pwd/grp.h) \
    $(wildcard include/config/lfs.h) \
    $(wildcard include/config/feature/buffers/go/on/stack.h) \
    $(wildcard include/config/feature/buffers/go/in/bss.h) \
    $(wildcard include/config/extra/cflags.h) \
    $(wildcard include/config/variable/arch/pagesize.h) \
    $(wildcard include/config/feature/verbose.h) \
    $(wildcard include/config/feature/etc/services.h) \
    $(wildcard include/config/feature/ipv6.h) \
    $(wildcard include/config/feature/seamless/xz.h) \
    $(wildcard include/config/feature/seamless/lzma.h) \
    $(wildcard include/config/feature/seamless/bz2.h) \
    $(wildcard include/config/feature/seamless/gz.h) \
    $(wildcard include/config/feature/seamless/z.h) \
    $(wildcard include/config/float/duration.h) \
    $(wildcard include/config/feature/check/names.h) \
    $(wildcard include/config/feature/prefer/applets.h) \
    $(wildcard include/config/long/opts.h) \
    $(wildcard include/config/feature/pidfile.h) \
    $(wildcard include/config/feature/syslog.h) \
    $(wildcard include/config/feature/syslog/info.h) \
    $(wildcard include/config/warn/simple/msg.h) \
    $(wildcard include/config/feature/individual.h) \
    $(wildcard include/config/shell/ash.h) \
    $(wildcard include/config/shell/hush.h) \
    $(wildcard include/config/echo.h) \
    $(wildcard include/config/sleep.h) \
    $(wildcard include/config/printf.h) \
    $(wildcard include/config/test.h) \
    $(wildcard include/config/test1.h) \
    $(wildcard include/config/test2.h) \
    $(wildcard include/config/kill.h) \
    $(wildcard include/config/killall.h) \
    $(wildcard include/config/killall5.h) \
    $(wildcard include/config/chown.h) \
    $(wildcard include/config/ls.h) \
    $(wildcard include/config/xxx.h) \
    $(wildcard include/config/route.h) \
    $(wildcard include/config/feature/hwib.h) \
    $(wildcard include/config/desktop.h) \
    $(wildcard include/config/feature/crond/d.h) \
    $(wildcard include/config/feature/setpriv/capabilities.h) \
    $(wildcard include/config/run/init.h) \
    $(wildcard include/config/feature/securetty.h) \
    $(wildcard include/config/pam.h) \
    $(wildcard include/config/use/bb/crypt.h) \
    $(wildcard include/config/feature/adduser/to/group.h) \
    $(wildcard include/config/feature/del/user/from/group.h) \
    $(wildcard include/config/ioctl/hex2str/error.h) \
    $(wildcard include/config/feature/editing.h) \
    $(wildcard include/config/feature/editing/history.h) \
    $(wildcard include/config/feature/tab/completion.h) \
    $(wildcard include/config/feature/username/completion.h) \
    $(wildcard include/config/feature/editing/fancy/prompt.h) \
    $(wildcard include/config/feature/editing/savehistory.h) \
    $(wildcard include/config/feature/editing/vi.h) \
    $(wildcard include/config/feature/editing/save/on/exit.h) \
    $(wildcard include/config/pmap.h) \
    $(wildcard include/config/feature/show/threads.h) \
    $(wildcard include/config/feature/ps/additional/columns.h) \
    $(wildcard include/config/feature/topmem.h) \
    $(wildcard include/config/feature/top/smp/process.h) \
    $(wildcard include/config/pgrep.h) \
    $(wildcard include/config/pkill.h) \
    $(wildcard include/config/pidof.h) \
    $(wildcard include/config/sestatus.h) \
    $(wildcard include/config/unicode/support.h) \
    $(wildcard include/config/feature/mtab/support.h) \
    $(wildcard include/config/feature/clean/up.h) \
    $(wildcard include/config/feature/devfs.h) \
  include/platform.h \
    $(wildcard include/config/werror.h) \
    $(wildcard include/config/big/endian.h) \
    $(wildcard include/config/little/endian.h) \
    $(wildcard include/config/nommu.h) \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/limits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/limits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/cdefs.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/versioning.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/api-level.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/get_device_api_level_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/ndk-version.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/float.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/limits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/posix_limits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/byteswap.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/endian.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stdint.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/stdint.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/wchar_limits.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stddef.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/endian.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stdbool.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/features.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/unistd.h \
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
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/select.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/time.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/time_types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/signal.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/sigcontext.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/signal_types.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/signal.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/signal.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/signal-defs.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/siginfo.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/siginfo.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/timespec.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/ucontext.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/user.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_signal_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/fcntl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/getopt.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/ioctl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/lockf.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/seek_constants.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/sysconf.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/string.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/xlocale.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/strcasecmp.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/strings.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_strings_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/ctype.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/dirent.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/errno.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/errno.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/errno.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/errno.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/errno-base.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_errno_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/fcntl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/fadvise.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/falloc.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/fcntl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/fcntl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/fcntl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/stat.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/uio.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/local/include/inttypes.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/inttypes.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/inttypes.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/netdb.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/sockios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/sockios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/sockios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/sa_family_t.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/setjmp.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/paths.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/stdio.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stdarg.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/local/include/stdlib.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/stdlib.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/alloca.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/wait.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/wait.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/malloc.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_stdlib_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/libgen.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/poll.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/poll.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/poll.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/poll.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/time.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/time.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/ioctl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/ioctl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/ioctl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/ioctl.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/termios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/termios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/termios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/termbits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/termbits.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/ioctls.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/ioctls.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/tty.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/mman.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/memfd.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/hugetlb_encode.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/mman.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/mman.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/mman.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/mman-common.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_sys_mman_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/resource.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/resource.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/resource.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/resource.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/stat.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_sys_stat_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/sysmacros.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/wait.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_sys_wait_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/termios.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_termios_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/param.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/param.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/param.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/param.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/pwd.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/grp.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/mntent.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/statfs.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/vfs.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/magic.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/arpa/inet.h \
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
  include/xatonum.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/syscall.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/unistd.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/unistd_64.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/glibc-syscalls.h \

libbb/missing_syscalls.o: $(deps_libbb/missing_syscalls.o)

$(deps_libbb/missing_syscalls.o):
