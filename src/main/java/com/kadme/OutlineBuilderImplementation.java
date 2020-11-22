package com.kadme;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OutlineBuilderImplementation implements OutlineBuilder {

    private List<Point> linesPoints;

    @Override
    public Polygon buildOutline(Set<Line> lines) {
        separatePoints(lines);

        Polygon result = determineOutlineRectangle();
        
        return result;
    }

    private void separatePoints(Set<Line> lines) {
        linesPoints = lines.stream()
                .map(
                        line -> new ArrayList<Point>(){{ add(line.getP1()); add(line.getP2()); }}
                ).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private Polygon determineOutlineRectangle() {
        double minX = linesPoints.stream().min(Comparator.comparingDouble(p -> p.getX())).map(Point::getX).get();
        double maxX = linesPoints.stream().max(Comparator.comparingDouble(p -> p.getX())).map(Point::getX).get();
        double minY = linesPoints.stream().min(Comparator.comparingDouble(p -> p.getY())).map(Point::getY).get();
        double maxY = linesPoints.stream().max(Comparator.comparingDouble(p -> p.getY())).map(Point::getY).get();

        return new Polygon(new ArrayList<Point>(){{
            add(new Point(minX,minY));
            add(new Point(minX,maxY));
            add(new Point(maxX,maxY));
            add(new Point(maxX,minY));
        }});
    }
}
