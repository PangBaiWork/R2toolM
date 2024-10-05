cmake_minimum_required(VERSION 3.22.1)
project("r2")

message("Build radare2 ${ANDROID_ABI}")


execute_process(
        COMMAND export PATH=$PATH:/home/pangbai/tmp/ndk/${ANDROID_ABI}/bin/ make -j6 # 替换为你想要执行的shell命令
        WORKING_DIRECTORY ${PROJECT_SOURCE_DIR}
        RESULT_VARIABLE result
        OUTPUT_VARIABLE output
)


if(result EQUAL "0")
    message( "Build succeeded: ${output}")
else()
    message( "Build failed: ${result}")
endif()
