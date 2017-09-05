cmake_minimum_required(VERSION 3.8)
project(helloWorld)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp Plane.cpp Plane.h)
add_executable(helloWorld ${SOURCE_FILES})