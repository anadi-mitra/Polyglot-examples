import math
def calc_distance(p1,p2):
    if p1 is not None and p2 is not None:
        dX,dY=p1.x-p2.x,p1.y-p2.y
        return math.sqrt(dX*dX+dY*dY)
    return "invalid operations"