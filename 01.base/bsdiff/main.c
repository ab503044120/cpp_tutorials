#include <stdio.h>

int main() {
    char *file = "d:\\1.txt";
    char *file1 = "d:\\1.1.txt";
    char *fileout = "d:\\1dif.txt";
    FILE *fp = fopen(file, "rw");
    FILE *fp1 = fopen(file1, "rw");
    FILE *fpout = fopen(file1, "rw");

    return 0;
}