cmd_libres/gai_strerror.o := x86_64-linux-android-gcc -Wp,-MD,libres/.gai_strerror.o.d  -std=gnu99 -Iinclude -Ilibbb  -include include/autoconf.h -D_GNU_SOURCE -DNDEBUG -D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 -DBB_VER='"1.36.0"' -Wall -Wshadow -Wwrite-strings -Wundef -Wstrict-prototypes -Wunused -Wunused-parameter -Wunused-function -Wunused-value -Wmissing-prototypes -Wmissing-declarations -Wno-format-security -Wdeclaration-after-statement -Wold-style-definition -finline-limit=0 -fno-builtin-strlen -fomit-frame-pointer -ffunction-sections -fdata-sections -funsigned-char -static-libgcc -falign-functions=1 -falign-jumps=1 -falign-labels=1 -falign-loops=1 -fno-unwind-tables -fno-asynchronous-unwind-tables -fno-builtin-printf -g -Oz --sysroot="/home/pangbai/tmp/ndk/x86_64/sysroot/"    -DKBUILD_BASENAME='"gai_strerror"'  -DKBUILD_MODNAME='"gai_strerror"' -c -o libres/gai_strerror.o libres/gai_strerror.c

deps_libres/gai_strerror.o := \
  libres/gai_strerror.c \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/socket.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/cdefs.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/versioning.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/api-level.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/get_device_api_level_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/ndk-version.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/types.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stddef.h \
  /home/pangbai/tmp/ndk/x86_64/lib64/clang/9.0.8/include/stdint.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/stdint.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/wchar_limits.h \
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
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/netdb.h \

libres/gai_strerror.o: $(deps_libres/gai_strerror.o)

$(deps_libres/gai_strerror.o):
