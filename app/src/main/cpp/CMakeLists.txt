cmake_minimum_required(VERSION 3.22.1)
project("top")


execute_process(
        COMMAND ./buildNative.sh  ${ANDROID_ABI} # 替换为你想要执行的shell命令
        WORKING_DIRECTORY ${PROJECT_SOURCE_DIR}
        RESULT_VARIABLE result
        OUTPUT_VARIABLE output
)


ADD_SUBDIRECTORY(termux)
ADD_SUBDIRECTORY(r2toolm)
ADD_SUBDIRECTORY(exec)
