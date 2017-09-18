#include <iostream>

using namespace std;

class A {
public :
    int a;

    A(int a);

    int getA();
};

int A::getA() {
    return a;
}

A::A(int a) : a(a) {}

class A1 : public virtual A {
public :
    int a1;

    int getA1();

    A1();

    A1(int a);
};

A1::A1(int a) : A(a) {
    a1 = a;
}

int A1::getA1() {
    return a1;
}

A1::A1() : A(1) {

}

class A2 : public virtual A {
public :
    int a2;

    int getA2();

    A2();

    A2(int a);
};

A2::A2(int a) : A(a) {
    a2 = a;
}

A2::A2() : A(11) {

}

int A2::getA2() {
    return a2;
}

class B : public A1, public A2 {
public:
    B();
};

B::B():A(11) {

}

int main() {
    B b;
    cout << b.A2::getA() <<endl;
    cout << sizeof(A)<<endl;
    cout << sizeof(A1)<<endl;
    cout << sizeof(A2)<<endl;
    cout << sizeof(B)<<endl;
    return 0;
}