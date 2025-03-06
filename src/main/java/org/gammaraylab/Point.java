package org.gammaraylab;

import org.graalvm.polyglot.HostAccess;

public class Point {
    @HostAccess.Export
    public double x;
    @HostAccess.Export
    public double y;
    public Point(){

    }
    public Point(double x, double y) {
//        if(y*y!=x*x*x-3*x+9)
//            throw new PointArithmeticException("invalid point ("+x+","+y+")");
        this.x=x;
        this.y=y;
    }
    //make a new point
    @HostAccess.Export
    public Point newPoint(double x, double y){
        return new Point(x,y);
    }
    @HostAccess.Export
    public boolean areEqual(Point other){
        return this.equals(other);
    }
    @HostAccess.Export
    @Override
    public boolean equals(Object other){
        if(other==null)
            return false;
        if(other instanceof Point){
            return this.x==((Point) other).x && this.y==((Point) other).y;
        }
        return false;
    }

    @HostAccess.Export
    public void printPoint(){
        System.out.println("("+this.x+","+this.y+")");
    }

    @Override
    public String toString(){
        return  "("+this.x+","+this.y+")";
    }
}
