#include <iostream>
#include "Plane.h"

int main() {
    Plane plane(1990, "fly");
    Plane &p1 = plane;
    Plane *p2 = new Plane(1992, "fly1");
    std::cout << plane.getName() << std::endl;
    delete p2;
    return 0;
}