package com.kadme.util;

import com.kadme.Line;
import com.kadme.Point;

public class GeometryCalculator {
    public static Double distance(Point a, Point b) {
        return Math.sqrt( Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2) );
    }

    public static Point findIntersectionPoint(Line lineA, Line lineB) {
        LineEquation function1 = new LineEquation(lineA);
        LineEquation function2 = new LineEquation(lineB);

        Double x = ( function2.b - function1.b ) / ( function1.a - function2.a );
        Double y = function1.findY(x);
        Point functionsIntersectionPoint = new Point(x,y);

        if (lineA.containsPoint(functionsIntersectionPoint) && lineB.containsPoint(functionsIntersectionPoint)) {
            return functionsIntersectionPoint;
        }
        else {
            return null;
        }
    }
}
