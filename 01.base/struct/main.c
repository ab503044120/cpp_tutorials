#include <stdio.h>


struct book {
    char *name;
    int price;
};

int main() {
    struct book cprimer;
    cprimer.name = "c primer";
    cprimer.price = 10;
    return 0;
}