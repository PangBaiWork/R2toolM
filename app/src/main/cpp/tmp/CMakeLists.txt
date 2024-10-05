cmake_minimum_required(VERSION 3.22.1)
project("r2")

message(STATUS "Command succeeded:")


execute_process(
        COMMAND  sys/android-build.sh arm64-static # 替换为你想要执行的shell命令
        WORKING_DIRECTORY ${PROJECT_SOURCE_DIR}
        RESULT_VARIABLE result
        OUTPUT_VARIABLE output
)


if(result EQUAL "0")
    message(STATUS "Command succeeded: ${output}")
else()
    message(STATUS "Command failed: ${result}")
endif()