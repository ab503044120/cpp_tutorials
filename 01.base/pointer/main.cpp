#include <iostream>

int main() {
    char *p;
    char a = '1';
    *p = a;
    std::cout << *p << std::endl;

    //指针数组:元素都是指针的数组
    int *q[10];
    int b[5][10] = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}};
    q[0] = b[0];
    q[1] = b[1];
    std::cout << q[1] << std::endl;

    //数组指针:指向数组的指针
    int (*j)[10];
    j = b;
    std::cout << *(j + 1) << std::endl;

    return 0;
}

