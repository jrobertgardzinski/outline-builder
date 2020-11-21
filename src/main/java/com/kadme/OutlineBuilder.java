package com.kadme;

import java.util.Set;

public interface OutlineBuilder {

    Polygon buildOutline(Set<Line> lines);
}
