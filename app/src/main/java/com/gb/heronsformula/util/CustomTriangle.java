package com.gb.heronsformula.util;

public class CustomTriangle {
    private static final int DIVIDER_BY_TWO = 2;

    /**
     * Calculates the area of the triangle whose lengths of edges are given.
     *
     * @param lengthOfEdgeA The length of edge A.
     * @param lengthOfEdgeB The length of edge B.
     * @param lengthOfEdgeC The length of edge C.
     * @return Returns the calculated area using Heron's Formula.
     */
    public static double getArea(double lengthOfEdgeA, double lengthOfEdgeB, double lengthOfEdgeC) {
        double semiPerimeter = getSemiPerimeter(lengthOfEdgeA, lengthOfEdgeB, lengthOfEdgeC);
        return CustomMath.sqrt((semiPerimeter * (semiPerimeter - lengthOfEdgeA) * (semiPerimeter - lengthOfEdgeB) * (semiPerimeter - lengthOfEdgeC)));
    }

    /**
     * Calculates the semi-perimeter of the triangle whose lengths of edges are given.
     *
     * @param lengthOfEdgeA The length of edge A.
     * @param lengthOfEdgeB The length of edge B.
     * @param lengthOfEdgeC The length of edge C.
     * @return Returns the calculated semi-perimeter.
     */
    public static double getSemiPerimeter(double lengthOfEdgeA, double lengthOfEdgeB, double lengthOfEdgeC) {
        return ((lengthOfEdgeA + lengthOfEdgeB + lengthOfEdgeC) / DIVIDER_BY_TWO);
    }
}
