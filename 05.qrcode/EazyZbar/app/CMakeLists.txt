# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
configure_file (
        src/main/cpp/libiconv-1.15/include/iconv.h.in
        src/main/cpp/libiconv-1.15/include/iconv.h
)
configure_file (
        src/main/cpp/libiconv-1.15/libcharset/include/localcharset.h.in
        src/main/cpp/libiconv-1.15/libcharset/include/localcharset.h
)

set(LIBICONV_INCLUDE
        ${CMAKE_BINARY_DIR}/src/main/cpp/libiconv-1.15/include
        ${CMAKE_BINARY_DIR}/src/main/cpp/libiconv-1.15/libcharset/include
)
include_directories(
        src/main/cpp/include
        src/main/cpp/include/zbar
)
set(LIBICONV_SRC
        src/main/cpp/libiconv-1.15/lib/iconv.c
        src/main/cpp/libiconv-1.15/libcharset/lib/localcharset.c
        src/main/cpp/libiconv-1.15/lib/relocatable.c)
set(ZBAR_SRC
        src/main/cpp/zbar/img_scanner.c
        src/main/cpp/zbar/decoder.c
        src/main/cpp/zbar/image.c
        src/main/cpp/zbar/symbol.c
        src/main/cpp/zbar/convert.c
        src/main/cpp/zbar/config.c
        src/main/cpp/zbar/scanner.c
        src/main/cpp/zbar/error.c
        src/main/cpp/zbar/refcnt.c
        src/main/cpp/zbar/video.c
        src/main/cpp/zbar/video/null.c
        src/main/cpp/zbar/decoder/code128.c
        src/main/cpp/zbar/decoder/code39.c
        src/main/cpp/zbar/decoder/code93.c
        src/main/cpp/zbar/decoder/codabar.c
        src/main/cpp/zbar/decoder/databar.c
        src/main/cpp/zbar/decoder/ean.c
        src/main/cpp/zbar/decoder/i25.c
        src/main/cpp/zbar/decoder/qr_finder.c
        src/main/cpp/zbar/qrcode/bch15_5.c
        src/main/cpp/zbar/qrcode/binarize.c
        src/main/cpp/zbar/qrcode/isaac.c
        src/main/cpp/zbar/qrcode/qrdec.c
        src/main/cpp/zbar/qrcode/qrdectxt.c
        src/main/cpp/zbar/qrcode/rs.c
        src/main/cpp/zbar/qrcode/util.c)

add_library(LIBICONV STATIC ${LIBICONV_SRC})
link_libraries(LIBICONV)
add_library(ZBAR STATIC ${ZBAR_SRC})
link_libraries(ZBAR)

add_library(
        zbarjni
        SHARED
        src/main/cpp/native-lib.cpp
        src/main/cpp/zbarjni.c )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        zbarjni

        # Links the target library to the log library
        # included in the NDK.
        ZBAR
        LIBICONV
        ${log-lib} )