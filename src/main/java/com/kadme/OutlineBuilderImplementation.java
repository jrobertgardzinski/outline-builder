package com.kadme;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OutlineBuilderImplementation implements OutlineBuilder {

    @Override
    public Polygon buildOutline(Set<Line> lines) {
        List<Point> linePoints = lines.stream()
                .map(
                    line -> new ArrayList<Point>(){{ add(line.getP1()); add(line.getP2()); }}
                ).flatMap(List::stream)
                .collect(Collectors.toList());
        List<Double> xCoordinates = linePoints.stream().map(Point::getX).collect(Collectors.toList());
        double minX = linePoints.stream().min(Comparator.comparingDouble(p -> p.getX())).map(Point::getX).get();
        double maxX = linePoints.stream().max(Comparator.comparingDouble(p -> p.getX())).map(Point::getX).get();
        double minY = linePoints.stream().min(Comparator.comparingDouble(p -> p.getY())).map(Point::getY).get();
        double maxY = linePoints.stream().max(Comparator.comparingDouble(p -> p.getY())).map(Point::getY).get();
        Polygon outlineRectangle = new Polygon(new ArrayList<Point>(){{
            add(new Point(minX,minY));
            add(new Point(minX,maxY));
            add(new Point(maxX,maxY));
            add(new Point(maxX,minY));
        }});
        return outlineRectangle;
    }
}
