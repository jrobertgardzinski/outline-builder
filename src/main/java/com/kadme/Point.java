package com.kadme;

import java.util.Objects;

public class Point implements Comparable<Point>{
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return almostEqual(point.x, x, 1E-7)
                && almostEqual(point.y, y, 1E-7);
    }

    private boolean almostEqual(double a, double b, double eps){
        return Math.abs(a-b)<eps;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Point o) {
        Double x = this.x;
        return x.compareTo(o.getX());
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
