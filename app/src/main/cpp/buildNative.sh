ARCH="arm64"
#cd busybox && make clean && cd ..
if [ "$1" = "x86_64" ]; then
    ARCH="x86_64"
    echo "The architecture is x86_64."
     find ./busybox -type f  ! -name "*.c"  ! -name "*.h"  ! -name "*.S"  ! -name "*.o" -exec sed -i 's/aarch64/x86_64/g' {} +
else
    ARCH="aarch64"
    echo "The architecture is arm64."
    find ./busybox -type f  ! -name "*.c"  ! -name "*.h"  ! -name "*.S" -exec sed -i 's/x86_64/aarch64/g' {} +

fi
cd radare2
./sys/android-build.sh $ARCH
export PATH=$PATH:/home/pangbai/tmp/ndk/$ARCH/bin/
cd ../busybox
make clean
make -j6
cp busybox ../../assets/.
