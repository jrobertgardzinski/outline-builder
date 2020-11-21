package com.kadme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    private static Line line1 = new Line(new Point(1,1), new Point(2,2));
    private static Line line2 = new Line(new Point(1,1), new Point(2,2));

    @Test
    void shouldBeEqual() {
        assertEquals(line1, line2);
    }
}