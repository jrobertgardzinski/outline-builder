package com.kadme;

import java.util.List;
import java.util.Objects;

public class Polygon {

    private List<Point> points;

    public Polygon(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Polygon polygon = (Polygon) o;
        return points.size() == polygon.points.size()
                && points.stream().allMatch(polygon.points::contains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }
}
