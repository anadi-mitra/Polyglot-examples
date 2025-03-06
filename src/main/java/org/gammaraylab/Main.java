 package org.gammaraylab;

 import org.graalvm.nativeimage.PinnedObject;

 public class Main {

     public static void main(String[] args) throws Exception {
     TestCases tc= new TestCases();
     tc.passingJavaObj();
//         int secretKey=2;
//         ECC ecc= new ECC();
//         Point generator=new Point(0,3);
//         Point pub= ecc.scalarMultiplication(secretKey,generator);
//         pub.printPoint();
     }

 }