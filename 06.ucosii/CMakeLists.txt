cmake_minimum_required(VERSION 3.8)
project(06_ucosii)

set(CMAKE_C_STANDARD 99)
include_directories(CONFIG CORE PORT user)
set(SOURCE_FILES
        #test.c
        main.c
        user/usercode.c
        CORE/ucos_ii.c
        PORT/os_cpu_c.c
        PORT/os_trace.c
        )
add_executable(06_ucosii ${SOURCE_FILES})
TARGET_LINK_LIBRARIES(06_ucosii WinMM.Lib)