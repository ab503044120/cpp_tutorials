#include <stdio.h>
#include <stdlib.h>

int main() {
    int *p = malloc(sizeof(int) * 1024 * 1024 );
    for (int i = 0; i < 1024 * 1024 ; i++) {
        p[i] = i;
    }
    printf("%d\n", p[1000]);
    free(p);
    return 0;
}