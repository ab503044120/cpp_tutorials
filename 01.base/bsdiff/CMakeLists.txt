cmake_minimum_required(VERSION 3.8)
project(bsdiff)

set(CMAKE_C_STANDARD 11)

set(SOURCE_FILES main.c bsdiff.c bspatch.c bzlib_private.h bzlib.h bzlib.c)
add_executable(bsdiff ${SOURCE_FILES})