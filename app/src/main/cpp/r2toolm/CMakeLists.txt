# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

# Declares the project name. The project name can be accessed via ${ PROJECT_NAME},
# Since this is the top level CMakeLists.txt, the project name is also accessible
# with ${CMAKE_PROJECT_NAME} (both CMake variables are in-sync within the top level
# build script scope).
project("r2toolm")
set(CMAKE_PROJECT_NAME "r2toolm")

include_directories(
        ${CMAKE_SOURCE_DIR}/radare2/libr/include
        ${CMAKE_SOURCE_DIR}/radare2/libr/../shlr/sdb/include
)



file(GLOB LIB_FILES "${CMAKE_SOURCE_DIR}/../libs/${ANDROID_ABI}/*.so")

foreach(LIB_FILE ${LIB_FILES})
    # 使用get_filename_component去除路径部分，只留下文件名
    message("lib added : ${LIB_FILE}")
    LINK_LIBRARIES(${LIB_FILE})
endforeach()
#LINK_LIBRARIES(${CMAKE_SOURCE_DIR}/../libs/${ANDROID_ABI}/libr_socket.so)

add_library(${CMAKE_PROJECT_NAME} SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        native-lib.cpp
        tool.cpp
        redirect.cpp)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")





target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        android
        log)


# build executable for test
add_executable(test
        native-lib.cpp
        tool.cpp
        redirect.cpp)

target_link_libraries(test
        # List libraries link to the target library
        android
        log)

