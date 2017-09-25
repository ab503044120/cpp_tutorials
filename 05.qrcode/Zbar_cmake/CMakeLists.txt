cmake_minimum_required(VERSION 3.8)
project(Zbar_cmake)

set(CMAKE_C_STANDARD 11)

set(LIBICONV_INCLUDE
        libiconv-1.15/include
        libiconv-1.15/libcharset
        libiconv-1.15/libcharset/include)
set(ZBAR_INCLUDE
        zbar)
set(ZLIB_INCLUDE
        zlib)
set(PNG_INCLUDE
        libpng)
include_directories(${LIBICONV_INCLUDE} ${LIBICONV_INCLUDE} ${PNG_INCLUDE} include ${ZLIB_INCLUDE})


set(LIBICONV_SRC libiconv-1.15/lib/iconv.c
        libiconv-1.15/libcharset/lib/localcharset.c
        libiconv-1.15/lib/relocatable.c)

set(ZBAR_SRC zbar/img_scanner.c
        zbar/decoder.c
        zbar/image.c
        zbar/symbol.c
        zbar/convert.c
        zbar/config.c
        zbar/scanner.c
        zbar/error.c
        zbar/refcnt.c
        zbar/video.c
        zbar/video/null.c
        zbar/decoder/code128.c
        zbar/decoder/code39.c
        zbar/decoder/code93.c
        zbar/decoder/codabar.c
        zbar/decoder/databar.c
        zbar/decoder/ean.c
        zbar/decoder/i25.c
        zbar/decoder/qr_finder.c
        zbar/qrcode/bch15_5.c
        zbar/qrcode/binarize.c
        zbar/qrcode/isaac.c
        zbar/qrcode/qrdec.c
        zbar/qrcode/qrdectxt.c
        zbar/qrcode/rs.c
        zbar/qrcode/util.c)

set(LIBPNG_SRC libpng/pngget.c
        libpng/pngread.c
        libpng/pngrutil.c
        libpng/pngtrans.c
        libpng/pngwtran.c
        libpng/png.c
        libpng/pngmem.c
        libpng/pngrio.c
        libpng/pngset.c
        libpng/pngwio.c
        libpng/pngwutil.c
        libpng/pngerror.c
        libpng/pngpread.c
        libpng/pnggccrd.c
        libpng/pngtest.c
        libpng/pngrtran.c
        libpng/pngvcrd.c
        libpng/pngwrite.c)

set(ZLIB_SRCS
        zlib/adler32.c
        zlib/compress.c
        zlib/crc32.c
        zlib/deflate.c
        zlib/gzclose.c
        zlib/gzlib.c
        zlib/gzread.c
        zlib/gzwrite.c
        zlib/inflate.c
        zlib/infback.c
        zlib/inftrees.c
        zlib/inffast.c
        zlib/trees.c
        zlib/uncompr.c
        zlib/zutil.c
        )

add_library(ZLIB STATIC ${ZLIB_SRCS})
link_libraries(ZLIB)
add_library(LIBPNG STATIC ${LIBPNG_SRC})
link_libraries(LIBPNG)
add_library(LIBICONV STATIC ${LIBICONV_SRC})
link_libraries(LIBICONV)
add_library(ZBAR STATIC ${ZBAR_SRC})
link_libraries(ZBAR)
set(SRC scan_image.c)
add_executable(Zbar_cmake ${SRC})
#add_executable(Zbar_cmake ${LIBICONV} ${ZBAR} ${LIBPNG_SRC} ${SRC} ZLIB )
#TARGET_LINK_LIBRARIES(winmm.lib)
TARGET_LINK_LIBRARIES(Zbar_cmake WinMM.Lib)
