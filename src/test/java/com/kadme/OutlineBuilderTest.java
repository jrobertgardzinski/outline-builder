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
    class DeterminingOutlineRectangle {

        private final Point POINT_0_0 = new Point(0,0);
        private final Point POINT_0_1 = new Point(0,1);
        private final Point POINT_1_1 = new Point(1,1);
        private final Point POINT_1_0 = new Point(1,0);

        private Set<Line> SQUARE;
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
        void polygonShouldBeBuiltOnParallelVerticalFromTheBeginning() {
            assertEquals(EXPECTED_RESULT, outlineBuilder.buildOutline(PARALLEL_VERTICAL));
        }

        @Test
        void polygonShouldBeBuiltOnParallelHorizontalFromTheBeginning() {
            assertEquals(EXPECTED_RESULT, outlineBuilder.buildOutline(PARALLEL_HORIZONTAL));
        }
    }

    @Nested
    class NaiveAlgorithm {
        private final Point POINT_A_PRIM = new Point(3,4);
        private final Point POINT_A_BIS = new Point(-4,-3);
        private final Point POINT_BC = new Point(1,0);
        private final Point POINT_B_PRIM = new Point(4,3);
        private final Point POINT_B_BIS = new Point(-3,-4);
        private final Point POINT_BD = new Point(0,-1);
        private final Point POINT_C_PRIM = new Point(4,-3);
        private final Point POINT_C_BIS = new Point(-3,4);
        private final Point POINT_AD = new Point(-1,0);
        private final Point POINT_D_PRIM = new Point(3,-4);
        private final Point POINT_D_BIS = new Point(-4,3);
        private final Point POINT_AC = new Point(0,1);

        private Set<Line> LINES;

        private Polygon EXPECTED_RESULT;

        private OutlineBuilder outlineBuilder;

        @BeforeEach
        void setUp() {
            outlineBuilder = new OutlineBuilderImplementation();

            LINES =  new HashSet<Line>() {{
                add(new Line(POINT_A_PRIM, POINT_A_BIS));
                add(new Line(POINT_B_PRIM, POINT_B_BIS));
                add(new Line(POINT_C_PRIM, POINT_C_BIS));
                add(new Line(POINT_D_PRIM, POINT_D_BIS));
            }};

            EXPECTED_RESULT = new Polygon(new ArrayList<Point>(){{
                add(POINT_A_PRIM);
                add(POINT_A_BIS);
                add(POINT_BC);
                add(POINT_B_PRIM);
                add(POINT_B_BIS);
                add(POINT_BD);
                add(POINT_C_PRIM);
                add(POINT_C_BIS);
                add(POINT_AD);
                add(POINT_D_PRIM);
                add(POINT_D_BIS);
                add(POINT_AC);
            }});
        }

        @Test
        void shouldEvaluateRubberBand() {
            assertEquals(EXPECTED_RESULT, outlineBuilder.buildOutline(LINES));
        }
    }
}