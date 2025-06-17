package mircobenchmarks.java;

import mircobenchmarks.java.exceptions.KeyNotFoundException;
import mircobenchmarks.polyglot.PythonContext;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Passing objects to python functions.
We are creating 'n' cartesian points and will find the distance between
two adjacent points in the list.
The Python function will calculate and return the distance in this case.
*/
public final class ObjectPassing implements Test{

    private static final String PYTHON_SRC_FILE_PATH="src/main/java/mircobenchmarks/python_scripts/ObjectPassing.py";

    List<ProxyPoint> initList(int n) {
        List<ProxyPoint> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = Math.random()*100D;
            double y = Math.random()*100D;
            list.add(new ProxyPoint(x, y));
        }
        return list;
    }

    public void runTest(){
        List<ProxyPoint> pointList=initList(200);
        /*
         Context is basically a virtual environment for running
         polyglot programs, here we are create a context for python
        */
        PythonContext pythonContext= new PythonContext();
        //load the python script
        File pySrcFile= new File(PYTHON_SRC_FILE_PATH);
        try {
            //get all the available bindings from the loaded python script
            Value bindings= pythonContext.getBindings(pySrcFile);
            //getting the desired function from python
            Value calc_distance=bindings.getMember("calc_distance");
            Value res;
            Double[] distList= new Double[pointList.size()-1];
            for(int i=0;i<pointList.size()-1;i++){
                //execute the python function by passing arguments using the polylgot context
                res=calc_distance.execute(pointList.get(i),pointList.get(i+1));
                if(res.isNumber())
                    distList[i]=res.asDouble();
                else if(res.isString())
                    System.out.println(res.asString());
                else
                    System.err.println(res);
            }
            System.out.println(Arrays.toString(distList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     Proxy class to pass obj to python and access them as native object
    */
    private static class ProxyPoint implements ProxyObject {
        private double x;
        private double y;

        public ProxyPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
        //resolves obj.x or obj.y in python
        @Override
        public Object getMember(String key) {
            return switch (key) {
                case "x" -> x;
                case "y" -> y;
                default -> throw new KeyNotFoundException("key " + key + " is not valid");
            };
        }
        //provides a list of available fields to python
        @Override
        public Object getMemberKeys() {
            return new String[]{"x", "y"};
        }

        @Override
        public boolean hasMember(String key) {
            return key.equals("x") || key.equals("y");
        }

        //handles field assignment in python
        @Override
        public void putMember(String key, Value value) {
            switch (key) {
                case "x" -> x = value.asDouble();
                case "y" -> y = value.asDouble();
                default -> throw new KeyNotFoundException("key " + key + " is not valid");
            }
        }
    }
}