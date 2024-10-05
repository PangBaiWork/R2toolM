cmd_libbb/perror_nomsg.o := x86_64-linux-android-gcc -Wp,-MD,libbb/.perror_nomsg.o.d  -std=gnu99 -Iinclude -Ilibbb  -include include/autoconf.h -D_GNU_SOURCE -DNDEBUG -D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 -DBB_VER='"1.36.0"' -Wall -Wshadow -Wwrite-strings -Wundef -Wstrict-prototypes -Wunused -Wunused-parameter -Wunused-function -Wunused-value -Wmissing-prototypes -Wmissing-declarations -Wno-format-security -Wdeclaration-after-statement -Wold-style-definition -finline-limit=0 -fno-builtin-strlen -fomit-frame-pointer -ffunction-sections -fdata-sections -funsigned-char -static-libgcc -falign-functions=1 -falign-jumps=1 -falign-labels=1 -falign-loops=1 -fno-unwind-tables -fno-asynchronous-unwind-tables -fno-builtin-printf -g -Oz --sysroot="/home/pangbai/tmp/ndk/x86_64/sysroot/"    -DKBUILD_BASENAME='"perror_nomsg"'  -DKBUILD_MODNAME='"perror_nomsg"' -c -o libbb/perror_nomsg.o libbb/perror_nomsg.c

deps_libbb/perror_nomsg.o := \
  libbb/perror_nomsg.c \
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

libbb/perror_nomsg.o: $(deps_libbb/perror_nomsg.o)

$(deps_libbb/perror_nomsg.o):
