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
    public Mapper<Double, Double> invertD() {
        return Data.mapper(d -> -d);
    }

    /**
     * Sums two doubles.
     * @return The new interpolator.
     */
    public Interpolator<Double, Double, Double> sumD() {
        return Data.inter((a, b) -> a + b);
    }

    /**
     * Multiplies two doubles.
     * @return The new interpolator.
     */
    public Interpolator<Double, Double, Double> multD() {
        return Data.inter((a, b) -> a * b);
    }

}
