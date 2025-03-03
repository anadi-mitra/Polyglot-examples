import math

import polyglot

class ComplexNumber:
    def __init__(self, real=0, imag=0):
        self.real = real
        self.imag = imag

    def __add__(self, other):
        cTmp= ComplexNumber(self.real, other.imag)
        return ComplexNumber(self.real + other.real + cTmp.real, self.imag + other.imag + cTmp.imag)

    def __str__(self):
        return f"({self.real} + {self.imag}i)"

def addNatural(a:int, b:int):
    return a+b
def addComplexLoop(n: int):
    res=0
    for i in range(n):
        c1 = ComplexNumber(i+i*n, n*3+i)
        c2 = ComplexNumber(n-i, n*4+i)
        sum_real= (c1.real+c2.real)%100000
        naturalSum=addNatural(i,i*i)%100000
        res+=sum_real+naturalSum
    return res

def sendObject():
    c1= ComplexNumber()
    n=10000
    for i in range(n):
        c1 = ComplexNumber(i*n, n+i)
        c2 = ComplexNumber(n-i, 4*i)
        sum_real= (c1.real+c2.real)%100000
        c2.real= c1.real
        c1.real= sum_real
    return c1

polyglot.export_value("ComplexNumber",ComplexNumber)