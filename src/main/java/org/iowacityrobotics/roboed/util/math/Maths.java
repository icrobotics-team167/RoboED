package org.iowacityrobotics.roboed.util.math;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

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

    /**
     * Inclusively clamps a value between a lower and upper boundary.
     * @param n The value to clamp.
     * @param lower The lower boundary.
     * @param upper The upper boundary.
     * @return The clamped value.
     */
    public static double clamp(double n, double lower, double upper) {
        return Math.max(Math.min(n, upper), lower);
    }

    /**
     * Thresholds a value by its absolute value.
     * @param n The value to threshold.
     * @param thresh The threshold.
     * @return The thresholded value.
     */
    public static double threshAbs(double n, double thresh) {
        return Math.abs(n) < thresh ? 0D : n;
    }

    /**
     * Takes the average of a set of numbers.
     * @param nums The numbers.
     * @return The average.
     */
    public static double average(double... nums) {
        return Arrays.stream(nums).sum() / (double)nums.length;
    }

    /**
     * Returns the maximum element in a set of objects.
     * @param key The function mapping an element to its comparison key.
     * @param set The set of objects.
     * @param <T> The type of objects in the set.
     * @param <V> The type of comparison key.
     */
    public static <T, V extends Comparable<V>> T max(Function<T, V> key, T... set) {
        return Arrays.stream(set).max(Comparator.comparing(key)).orElse(null);
    }

    /**
     * Returns the minimum element in a set of objects.
     * @param key The function mapping an element to its comparison key.
     * @param set The set of objects.
     * @param <T> The type of objects in the set.
     * @param <V> The type of comparison key.
     */
    public static <T, V extends Comparable<V>> T min(Function<T, V> key, T... set) {
        return Arrays.stream(set).min(Comparator.comparing(key)).orElse(null);
    }

}
