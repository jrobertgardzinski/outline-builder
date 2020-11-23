package com.kadme;

import com.kadme.util.LineEquation;

import java.util.Objects;

public class Line {

    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2) {
        if (p1.compareTo(p2) > 0) {
            this.p1 = p1;
            this.p2 = p2;
        }
        else {
            this.p1 = p2;
            this.p2 = p1;
        }
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public boolean containsPoint(Point point) {
        LineEquation function = new LineEquation(this);
        return function.containsPoint(point)
                && this.p1.getX() > point.getX() && point.getX() > this.p2.getX();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return p1.equals(line.p1) && p2.equals(line.p2)
                || p1.equals(line.p2) && p2.equals(line.p1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }
}
