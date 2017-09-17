#include <iostream>

using namespace std;

/*
 * �������캯���͵��ں�ͬʱ���ڵ�����
 */
class Base {

public :
    static int b;

public:
    int a;

    //���캯��
    Base();

    //���ι��캯��
    Base(int a);

    ~Base();

    //�������캯��
    Base(const Base &base);


    Base &operator=(Base &base);

};
int Base::b=0;
Base::Base() {
    a = 0;
    b++;
    cout << "�޲ι��캯��" << endl;
}

Base::Base(int a) : a(a) {
    cout << "�вι��캯��" << endl;
    b++;
}

Base::Base(const Base &base) {
    cout << "�������캯��" << endl;
    this->a = base.a;
}

Base &Base::operator=(Base &base) {
    cout << "=����" << endl;
    this->a = base.a;
    return *this;
}

Base::~Base() {
    cout << b << "��������" << endl;
}


int main() {
    Base base;
    cout << "base.a=" << base.a << endl;
    Base base1 = Base(2);
    cout << "base1.a=" << base1.a << endl;
    base1 = base;
    cout << "base1.a=" << base1.a << endl;
    return 0;
}
