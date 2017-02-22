package org.iowacityrobotics.roboed.data.inter;

import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.IStatefulData;

/**
 * Function that interpolates two data streams.
 * @author Evan Geng
 */
public abstract class Interpolator<I1, I2, O> implements IStatefulData {

    /**
     * Constructs an interpolator.
     */
    public Interpolator() {
        if (!(this instanceof Data.StatelessInterpolator))
            Data.registerStateful(this);
    }

    /**
     * Interpolates two data elements.
     * @param a Input element A.
     * @param b Input element B.
     * @return The output element.
     */
    public abstract O apply(I1 a, I2 b);

}
