def print_fibonacci_term(num):
    if num<=0:
        print("invalid")
    elif num==1:
        print("0")
    elif num==2:
        print("1")
    else:
        a,b=1,1
        for _ in range(2,num):
            a,b=b,a+b
        print(b)

def get_fibonacci_series(num):
    if num<=0:
        return "invalid"
    elif num==1:
        return "0"
    elif num==2:
        return "0 1"
    series=[0,1]
    a,b=0,1
    for _ in range(2,num+1):
        a,b=b,a+b
        series.append(b)
    return ' '.join(str(x) for x in series)