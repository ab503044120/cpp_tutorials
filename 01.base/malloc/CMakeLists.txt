cmake_minimum_required(VERSION 3.8)
project(malloc)

set(CMAKE_C_STANDARD 11)

set(SOURCE_FILES main.c)
add_executable(malloc ${SOURCE_FILES})