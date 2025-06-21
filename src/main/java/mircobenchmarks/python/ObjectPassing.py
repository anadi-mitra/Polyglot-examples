import math
from mircobenchmarks.python.SomeUserDefClass import Render

def calc_distance(p1,p2):
    if p1 is not None and p2 is not None:
        dX,dY=p1.x-p2.x,p1.y-p2.y
        return math.sqrt(dX*dX+dY*dY)
    return "invalid operations"

def draw_triangle(p1,p2,p3):
    a=calc_distance(p1,p2)
    b=calc_distance(p1,p3)
    c=calc_distance(p3,p2)
    print(a+b+c)
    Render.plot(None,p1,p2,p3)
