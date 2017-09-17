#include <iostream>

using namespace std;

/*
 * 拷贝构造函数和等于号同时存在的区分
 */
class Base {

public :
    static int b;

public:
    int a;

    //构造函数
    Base();

    //含参构造函数
    Base(int a);

    ~Base();

    //拷贝构造函数
    Base(const Base &base);


    Base &operator=(Base &base);

};
int Base::b=0;
Base::Base() {
    a = 0;
    b++;
    cout << "无参构造函数" << endl;
}

Base::Base(int a) : a(a) {
    cout << "有参构造函数" << endl;
    b++;
}

Base::Base(const Base &base) {
    cout << "拷贝构造函数" << endl;
    this->a = base.a;
}

Base &Base::operator=(Base &base) {
    cout << "=重载" << endl;
    this->a = base.a;
    return *this;
}

Base::~Base() {
    cout << b << "析构函数" << endl;
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
