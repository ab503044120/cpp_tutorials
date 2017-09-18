#include <iostream>

using namespace std;

class A {
public :
    A();

    virtual ~A();

    virtual void say();
};

void A::say() {
    cout << "����A" << endl;
}

A::A() {
    cout << "����A�Ĺ��캯��" << endl;
}

A::~A() {
    cout << "����A����������" << endl;
}

class A1 : public virtual A {
public :
    A1();

    ~A1() override;

public:
    void say() override;
};

A1::A1() {
    cout << "����A1�Ĺ��캯��" << endl;
}

A1::~A1() {
    cout << "����A1����������" << endl;
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