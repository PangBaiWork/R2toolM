cmake_minimum_required(VERSION 3.22.1)


project("exec")
set(CMAKE_PROJECT_NAME "exec")


add_library(${CMAKE_PROJECT_NAME} SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        termux-exec.c)
#set_target_properties(${CMAKE_PROJECT_NAME} PROPERTIES
#        LIBRARY_OUTPUT_DIRECTORY_DEBUG "${CMAKE_SOURCE_DIR}/../assets/"
#        LIBRARY_OUTPUT_DIRECTORY_RELEASE "${CMAKE_SOURCE_DIR}/../assets/"
#)
target_link_libraries(${CMAKE_PROJECT_NAME}
      # List libraries link to the target library
       android
       log)