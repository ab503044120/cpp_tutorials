#include <iostream>
#include <cstring>
#include <stdlib.h>

using namespace std;

class Anima {
public:
    int a;
    char *b;

    Anima();

    ~Anima();

    virtual void say();
};

void Anima::say() {
    cout << "����Anima" << endl;
}

Anima::Anima() {
    cout << "����Anima�Ĺ���" << endl;
    b = static_cast<char *>(malloc(sizeof(char) * 1000));
}

Anima::~Anima() {
    cout << "����Anima������ " << &b << endl;
    delete b;
}


class Lion : public Anima {
public:
    Lion();

    virtual ~Lion();

    void say() override;

};

void DuoTai1();

void DuoTai2();

void DuoTai3();

Lion::Lion() {
    cout << "����Lion�Ĺ���" << endl;
    strcpy(b, "Lion");
}

Lion::~Lion() {
    cout << "����Lion������" << &b << endl;
    //delete b;
}

void Lion::say() {
    cout << "����Lion" << endl;
}

int main() {
    DuoTai1();
//        DuoTai2();
//    DuoTai3();
    system("pause");
    return 0;
}

void DuoTai2() {
    Lion lion;
    Anima &anima1 = lion;
    lion.say();
    cout << "------------" << endl;
}

void DuoTai1() {
    //����ᷢ�������и�
    Anima anima = Lion();
    anima.say();
    cout << "------------" << endl;
}

void DuoTai3() {
    Lion *lion = new Lion();
    lion->say();
    delete lion;
    cout << "------------" << endl;
}