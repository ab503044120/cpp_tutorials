//
// Created by huihui on 2017/9/5.
//
#include <iostream>
#include "Plane.h"

int Plane::getYear() const {
    return year;
}

void Plane::setYear(int year) {
    Plane::year = year;
}

const string &Plane::getName() const {
    return name;
}

void Plane::setName(const string &name) {
    Plane::name = name;
}

Plane::Plane(int year, const string &name) : year(year), name(name) {
    cout << "plane  youcan gouzao" << endl;
}

Plane::~Plane() {
    cout << "plane " + this->name + "xigou" << endl;
}
