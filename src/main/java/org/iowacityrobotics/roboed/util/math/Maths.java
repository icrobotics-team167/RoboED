package org.iowacityrobotics.roboed.util.math;

/**
 * Mathematical utilities.
 * @author Evan Geng
 */
public class Maths {

    /**
     * Checks whether a number is within a given range, inclusive.
     * @param n The number to check.
     * @param lower The lower boundary.
     * @param upper The upper boundary.
     * @return Whether the number is in the range or not.
     */
    public static boolean inRange(int n, int lower, int upper) {
        return n >= lower && n <= upper;
    }
    
}
