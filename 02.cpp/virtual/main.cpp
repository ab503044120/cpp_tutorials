#include <iostream>

using namespace std;

class A {
public :
    A();

    virtual ~A();

    virtual void say();
};

void A::say() {
    cout << "我是A" << endl;
}

A::A() {
    cout << "我是A的构造函数" << endl;
}

A::~A() {
    cout << "我是A的析构函数" << endl;
}

class A1 : public virtual A {
public :
    A1();

    ~A1() override;

public:
    void say() override;
};

A1::A1() {
    cout << "我是A1的构造函数" << endl;
}

A1::~A1() {
    cout << "我是A1的析构函数" << endl;
}

void A1::say() {
    A::say();
}

int main() {
    A *a = new A1();
    A &a1 = *a;
    a->say();

    return 0;
}