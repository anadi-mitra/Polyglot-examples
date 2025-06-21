package mircobenchmarks.java;

import mircobenchmarks.polyglot.PythonContext;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/*
simple polyglot calls to python functions
*/
public class FunctionCalling implements Test{
    private static final String PYTHON_SRC_FILE_PATH="src/main/java/mircobenchmarks/python/FunctionCalling.py";

    @Override
    public void runTest(){
        PythonContext pythonContext= new PythonContext();
        Random rand= new Random();
        try {
            File pySrcFile= new File(PYTHON_SRC_FILE_PATH);
            Value bindings= pythonContext.getBindings(pySrcFile);
            //getting the desired function from python
            Value print_fibonacci_term=bindings.getMember("print_fibonacci_term");
            int terms= /*rand.nextInt(20)+*/500;
            //139423224561697880139724382870407283950070256587697307264108962948325571622863290691557658876222521294125
            print_fibonacci_term.execute(terms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }}
