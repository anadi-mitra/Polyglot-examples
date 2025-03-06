#Point shall point to an object of "Point" class

def scalarMultiplication(alpha, generatorPoint):
    res=generatorPoint
    for i in range(alpha):
        res=pointDoubling(generatorPoint)
    return res

def pointDoubling(point):
    slope=(3.0*point.x*point.x-3)/(2.0*point.y)
    x=slope*slope-2.0*point.x
    y=slope*(point.x-x)-point.y
    return Point.newPoint(x,y)

def processList(generatorList, secretKeyList):
    result = []
    for i in range(len(generatorList)):
        result.append(scalarMultiplication(secretKeyList[i], generatorList[i]))
    return result
