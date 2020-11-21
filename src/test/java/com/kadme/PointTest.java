package com.kadme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    private static Point POINT_1 = new Point(1,1);
    private static Point POINT_2 = new Point(1,1);

    @Test
    void shouldBeEqual() {
        assertEquals(POINT_1, POINT_2);
    }
}