#include <stdio.h>
//#define fun(a,b) a=b
#define me(a,b) (a ## b)=1

unsigned char ucT0=0,ucT1=0;
int i = 0;

int main( )
{
    me(ucT,0);
    printf("%d",ucT0);
    return 0;
}