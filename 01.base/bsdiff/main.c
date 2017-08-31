#include <stdio.h>
#include "bsdiff.h"
#include "bspatch.h"

int main() {
//    char *file = "d:\\1.apk";
//    char *file1 = "d:\\2.apk";
//    char *fileout = "d:\\1dif.apk";
//    char *fileresult = "d:\\result.apk";
    char *file = "d:\\1.txt";
    char *file1 = "d:\\1.1.txt";
    char *fileout = "d:\\1dif.txt";
    char *fileresult = "d:\\result.txt";
//    FILE *fp = fopen(file, "rw");
//    FILE *fp1 = fopen(file1, "rw");
//    FILE *fpout = fopen(file1, "rw");
//    FILE *fpresult = fopen(file1, "rw");

    char *a[] = {"哈哈哈", file, file1, fileout};
    bsdiff(4, a);

    char *b[] = {"哈哈哈", file, fileresult, fileout};
    bspatch(4, b);

    return 0;
}