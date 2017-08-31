#include <stdio.h>
#include <stdlib.h>

int main() {
    char *file = "d:\\1.txt";
    FILE *fp = fopen(file, "rw");
    if (fp == NULL) {
        printf("打开失败");
        return 0;
    }
    char buf[50];
    while (fgets(buf, 50, fp)) {
        printf("%s", buf);
    }
    printf("\n");
    fclose(fp);
    system("pause");
    return 0;
}