from mircobenchmarks.python.PythonInternalObjectPassing import SomeSharedClass

class Render:
    def plot(_,p1,p2,p3):
        print(p1.x+p2.x+p3.x)
        SomeSharedClass.GLOBAL_VAR=p1