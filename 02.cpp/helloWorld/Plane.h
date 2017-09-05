//
// Created by huihui on 2017/9/5.
//

#ifndef HELLOWORLD_PLANE_H
#define HELLOWORLD_PLANE_H

#include <string>
using namespace std;
class Plane {
public:
    virtual ~Plane();

    Plane(int year, const string &name);

    int getYear() const;

    void setYear(int year);

    const string &getName() const;

    void setName(const string &name);

private:
    int year;
    string name;
};
#endif //HELLOWORLD_PLANE_H
