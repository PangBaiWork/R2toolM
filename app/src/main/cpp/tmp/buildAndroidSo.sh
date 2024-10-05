#  ./configure --with-compiler=android --with-ostype=android --prefix=/data/data/work.pangbai.r2toolm/files    --without-gpl  --without-pic --with-nonpic
make -j16
ARCH=$NDK_ARCH
if [ "${BUILD}" = 1 ]; then

script_path=$(dirname "$0")

# 获取脚本的绝对路径
script_abs_path=$(cd "$script_path" && pwd)

libPath=/data/data/work.pangbai.r2toolm/files/lib/
output="$script_abs_path/build/$ARCH/libr2m.so"

make install DESTDIR=$script_abs_path/build/$ARCH

#!/bin/bash

# 目标共享库的名称

# 获取目录下所有的 .a 文件
archive_files=$(ls $script_abs_path/build/$ARCH$libPath*.a)

# 检查是否存在 .a 文件
if [ -z "$archive_files" ]; then
    echo "没有找到 .a 文件"
    exit 1
fi

# 使用 gcc 将 .a 文件链接成 .so 文件



$CC -shared -o $output -Wl,--whole-archive $archive_files -Wl,--no-whole-archive
# 检查是否成功创建 .so 文件
if [ -f "$output" ]; then
    echo "成功创建共享库：$output"
else
    echo "创建共享库失败"
    exit 1
fi
