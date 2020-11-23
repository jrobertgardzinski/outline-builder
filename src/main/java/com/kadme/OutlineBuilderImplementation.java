package com.kadme;

import com.google.common.collect.Iterables;
import com.kadme.util.GeometryCalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutlineBuilderImplementation implements OutlineBuilder {

    private List<Point> linesPoints;
    private List<Point> determinedPolygonPoints = new ArrayList<>();

    @Override
    public Polygon buildOutline(Set<Line> lines) {
        extractPoints(lines);

        Point startingPoint = findStartingPoint();
        determinedPolygonPoints.add(startingPoint);
        Point nextPoint = findClosestPoint(determinedPolygonPoints);

        while(nextPoint != null) {
            findIntersectionAndAddToDeterminedPolygonPoints(lines, Iterables.getLast(determinedPolygonPoints), nextPoint);
            
            determinedPolygonPoints.add(nextPoint);
            nextPoint = findClosestPoint(determinedPolygonPoints);
        }
        findIntersectionAndAddToDeterminedPolygonPoints(lines, Iterables.getLast(determinedPolygonPoints), startingPoint);

        return new Polygon(determinedPolygonPoints);
    }

    private void extractPoints(Set<Line> lines) {
        linesPoints = lines.stream()
                .map(
                        line -> new ArrayList<Point>(){{ add(line.getP1()); add(line.getP2()); }}
                ).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private Point findStartingPoint() {
        return linesPoints.stream().max(Comparator.comparing(Point::getY)).get();
    }

    private Point findClosestPoint(List<Point> points) {
        List<Point> filteredLinesPoints = linesPoints.stream()
                .filter(p -> !points.contains(p))
                .collect(Collectors.toList());
        if (filteredLinesPoints.size() == 0) {
            return null;
        }
        else {
            return filteredLinesPoints.stream()
                    .min(Comparator.comparing(p -> GeometryCalculator.distance(p, Iterables.getLast(points)))).get();
        }

    }

    private void findIntersectionAndAddToDeterminedPolygonPoints(Set<Line> lines, Point pointA, Point pointB) {
        Line lineA = lines.stream().filter(line -> constainsPoint(line, pointA)).findAny().get();
        Line lineB = lines.stream().filter(line -> constainsPoint(line, pointB)).findAny().get();

        Point pointOfIntersection = GeometryCalculator.findIntersectionPoint(lineA, lineB);

        if(pointOfIntersection != null) {
            determinedPolygonPoints.add(pointOfIntersection);
        }
    }

    private boolean constainsPoint(Line line, Point point) {
        return line.getP1().equals(point) || line.getP2().equals(point);
    }


}
