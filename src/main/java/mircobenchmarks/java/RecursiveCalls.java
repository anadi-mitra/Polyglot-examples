package mircobenchmarks.java;

import java.util.ArrayList;
import java.util.List;

public class RecursiveCalls implements Test{

    @Override
    public void runTest() {
        List<Long> series= new ArrayList<>();
        int n=50;
        if(n>0)
            series.add(0L);
        if(n>1)
            series.add(1L);
        if(n>2){
            generateFib(n,series);
        }
        System.out.print(series);
    }

    private void generateFib(int n, List<Long> series){
        if(n==3)
            series.add(1L);
        else {
            generateFib(n-1,series);
            series.add(series.get(n-2)+series.get(n-3));
        }
    }
}
