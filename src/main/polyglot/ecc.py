#"pointFactory" shall refer to an instance of "Point.class"


def scalarMultiplication(alpha, generatorPoint):
    res=generatorPoint
    for i in range(alpha):
        res=pointDoubling(res)  #O1
    return res

def pointDoubling(point):
    slope=(3.0*point.x*point.x-3)/(2.0*point.y)
    x=slope*slope-2.0*point.x
    y=slope*(point.x-x)-point.y

    # newPoint= Point.newPoint(x,y)   #O1
    # if(newPoint.isValid(point, newPoint)):
    #     return newPoint
    return pointFactory.newPoint(x,y)  #O2

def processList(generatorList, secretKeyList):
    result = []         #O1
    for i in range(len(generatorList)):
        result.append(scalarMultiplication(secretKeyList[i], generatorList[i])) #O1.append->O2
    return result
