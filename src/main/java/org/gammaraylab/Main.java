 package org.gammaraylab;

 public class Main {
    public static void main(String[] ignoredArgs) {
        System.out.println(loop());
    }

    static String loop(){
        TestCases tc= new TestCases();
        long i=10000L;
        tc.addComplexLoop(i);
        return "hello";
    }
 }
