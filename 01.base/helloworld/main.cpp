#include <iostream>
//#include "hello.h"
#include <cstdio>

extern "C" {
extern int add(int a, int b);
extern int a;
}

int main() {
    printf("hello\n");
    std::cout << add(1, a) << std::endl;
    std::cout << "Hello, World!" << std::endl;
    return 0;
}