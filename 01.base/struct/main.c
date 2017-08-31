#include <stdio.h>


struct book {
    char *name;
    int price;

    void (*p)(char *name, int price);

};

int add(int a, int b) {
    int c = a + b;
    return c;
}

void callback(char *name, int price) {
    printf("name:%s,price:%d", name, price);
}

int main() {
    struct book cprimer;
    cprimer.name = "c primer";
    cprimer.price = 10;
    cprimer.p = callback;
    cprimer.p(cprimer.name, cprimer.price);
    return 0;
}