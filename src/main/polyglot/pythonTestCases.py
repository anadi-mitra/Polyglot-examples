class ComplexNumber:
    def __init__(self, real, imag):
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
    for i in range(n):
        c1 = ComplexNumber(i+i*n, n*3+i)
        c2 = ComplexNumber(n-i, n*4+i)
        sum_result = c1 + c2
        sum_real= (c1.real+c2.real)%100000
        naturalSum=addNatural(i,i*i)%100000
        print(sum_real+naturalSum)

    print("-" * 30)