package com.kadme;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutlineBuilderTest {

    @Nested
    class InitialAssumptions {

        private final Point POINT_0_0 = new Point(0,0);
        private final Point POINT_0_1 = new Point(0,1);
        private final Point POINT_1_1 = new Point(1,1);
        private final Point POINT_1_0 = new Point(1,0);

        private Set<Line> SQUARE;
        private Set<Line> CROSS;
        private Set<Line> PARALLEL_VERTICAL;
        private Set<Line> PARALLEL_HORIZONTAL;

        private Polygon EXPECTED_RESULT;

        private OutlineBuilder outlineBuilder;

        @BeforeEach
        void setUp() {
            EXPECTED_RESULT = new Polygon(new ArrayList<Point>(){{
                add(POINT_0_0);
                add(POINT_0_1);
                add(POINT_1_1);
                add(POINT_1_0);
            }});
            outlineBuilder = new OutlineBuilderImplementation();

            SQUARE =  new HashSet<Line>() {{
                add(new Line(POINT_0_0, POINT_0_1));
                add(new Line(POINT_0_1, POINT_1_1));
                add(new Line(POINT_1_1, POINT_1_0));
                add(new Line(POINT_1_0, POINT_0_0));
            }};
            CROSS =  new HashSet<Line>() {{
                add(new Line(POINT_0_0, POINT_1_1));
                add(new Line(POINT_0_1, POINT_1_0));
            }};
            PARALLEL_VERTICAL =  new HashSet<Line>() {{
                add(new Line(POINT_0_0, POINT_0_1));
                add(new Line(POINT_1_1, POINT_1_0));
            }};
            PARALLEL_HORIZONTAL =  new HashSet<Line>() {{
                add(new Line(POINT_0_1, POINT_1_1));
                add(new Line(POINT_1_0, POINT_0_0));
            }};
        }

        @Test
        void polygonShouldBeBuiltOnSquareFromTheBeginning() {
            assertEquals(EXPECTED_RESULT, outlineBuilder.buildOutline(SQUARE));
        }

        @Test
        void polygonShouldBeBuiltOnCrossFromTheBeginning() {
            assertEquals(EXPECTED_RESULT, outlineBuilder.buildOutline(CROSS));
        }

        @Test
        void polygonShouldBeBuiltOnParallelVerticalFromTheBeginning() {
            assertEquals(EXPECTED_RESULT, outlineBuilder.buildOutline(PARALLEL_VERTICAL));
        }

        @Test
        void polygonShouldBeBuiltOnParallelHorizontalFromTheBeginning() {
            assertEquals(EXPECTED_RESULT, outlineBuilder.buildOutline(PARALLEL_HORIZONTAL));
        }
    }
}