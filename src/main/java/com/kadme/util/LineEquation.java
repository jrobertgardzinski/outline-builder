package com.kadme.util;

import com.kadme.Line;
import com.kadme.Point;

public class LineEquation {
    public final Double a;
    public final Double b;

    public LineEquation(Line line) {
        Point p1 = line.getP1();
        Point p2 = line.getP2()
                ;
        a = ( p2.getY() - p1.getY() ) / ( p2.getX() - p1.getX() );
        b = p1.getY() - a * p1.getX();
    }

    public Double findY(Double x) {
        return a*x + b;
    }

    public boolean containsPoint(Point point) {
        return this.findY(point.getX()).equals(point.getY());
    }
}
