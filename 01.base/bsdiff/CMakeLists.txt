cmake_minimum_required(VERSION 3.8)
project(bsdiff)

set(CMAKE_C_STANDARD 11)

set(SOURCE_FILES main.c bsdiff.c bzlib_private.h bzlib.h bzlib.c bsdiff.h crctable.c
        huffman.c decompress.c compress.c randtable.c blocksort.c bspatch.c bspatch.h)
add_executable(bsdiff ${SOURCE_FILES})