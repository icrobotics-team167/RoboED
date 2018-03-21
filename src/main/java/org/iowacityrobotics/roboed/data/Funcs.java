package org.iowacityrobotics.roboed.data;

import org.iowacityrobotics.roboed.data.inter.Interpolator;
import org.iowacityrobotics.roboed.data.mapper.Mapper;

/**
 * Simple and commonly-used data processing functions.
 * @author Evan Geng
 */
public class Funcs {

    /**
     * Inverts a double.
     * @return The new mapper.
     */
    public static Mapper<Double, Double> invertD() {
        return Data.mapper(d -> -d);
    }

    /**
     * Sums two doubles.
     * @return The new interpolator.
     */
    public static Interpolator<Double, Double, Double> sumD() {
        return Data.inter((a, b) -> a + b);
    }

    /**
     * Multiplies two doubles.
     * @return The new interpolator.
     */
    public static Interpolator<Double, Double, Double> multD() {
        return Data.inter((a, b) -> a * b);
    }

    /**
     * Performs the AND operation on two booleans.
     * @return The new interpolator.
     */
    public static Interpolator<Boolean, Boolean, Boolean> and() {
        return Data.inter((a, b) -> a && b);
    }

    /**
     * Performs the OR operation on two booleans.
     * @return The new interpolator.
     */
    public static Interpolator<Boolean, Boolean, Boolean> or() {
        return Data.inter((a, b) -> a || b);
    }

    /**
     * Performs the NAND operation on two booleans.
     * @return The new interpolator.
     */
    public static Interpolator<Boolean, Boolean, Boolean> nand() {
        return Data.inter((a, b) -> !(a && b));
    }

    /**
     * Performs the NOR operation on two booleans.
     * @return The new interpolator.
     */
    public static Interpolator<Boolean, Boolean, Boolean> nor() {
        return Data.inter((a, b) -> !(a || b));
    }


}
