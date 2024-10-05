cmd_libbb/ptr_to_globals.o := x86_64-linux-android-gcc -Wp,-MD,libbb/.ptr_to_globals.o.d  -std=gnu99 -Iinclude -Ilibbb  -include include/autoconf.h -D_GNU_SOURCE -DNDEBUG -D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 -DBB_VER='"1.36.0"' -Wall -Wshadow -Wwrite-strings -Wundef -Wstrict-prototypes -Wunused -Wunused-parameter -Wunused-function -Wunused-value -Wmissing-prototypes -Wmissing-declarations -Wno-format-security -Wdeclaration-after-statement -Wold-style-definition -finline-limit=0 -fno-builtin-strlen -fomit-frame-pointer -ffunction-sections -fdata-sections -funsigned-char -static-libgcc -falign-functions=1 -falign-jumps=1 -falign-labels=1 -falign-loops=1 -fno-unwind-tables -fno-asynchronous-unwind-tables -fno-builtin-printf -g -Oz --sysroot="/home/pangbai/tmp/ndk/x86_64/sysroot/"    -DKBUILD_BASENAME='"ptr_to_globals"'  -DKBUILD_MODNAME='"ptr_to_globals"' -c -o libbb/ptr_to_globals.o libbb/ptr_to_globals.c

deps_libbb/ptr_to_globals.o := \
  libbb/ptr_to_globals.c \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/errno.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/sys/cdefs.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/versioning.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/api-level.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/bits/get_device_api_level_inlines.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/ndk-version.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/linux/errno.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/x86_64-linux-android/asm/errno.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/errno.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/asm-generic/errno-base.h \
  /home/pangbai/tmp/ndk/x86_64/sysroot//usr/include/android/legacy_errno_inlines.h \

libbb/ptr_to_globals.o: $(deps_libbb/ptr_to_globals.o)

$(deps_libbb/ptr_to_globals.o):
