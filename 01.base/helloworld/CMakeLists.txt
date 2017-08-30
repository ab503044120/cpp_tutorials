cmake_minimum_required(VERSION 3.8)
project(helloworld)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp hello.c hello.h lib1.c lib1.h)
add_executable(helloworld ${SOURCE_FILES})