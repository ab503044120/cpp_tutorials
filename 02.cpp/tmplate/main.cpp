#include <iostream>

class B {
public:
    int get();
};

int B::get() {
    return 1;

}

template<typename T>
class A {
public:
    T b;

    A(T b) : b(b) {};

    int get();
//    {
//        return b.get();
//    }

};
template<typename T>
int A<T>::get() {
    return b.get();
    //b.get1();  ����д������Ȼ������ʾ����,�����ڱ����ʱ��ᱨ��
//    return b.get1();
}


int main() {
    B *b = new B;
    A<B> *a = new A<B>(*b);
    std::cout << a->get() << std::endl;
    return 0;
}