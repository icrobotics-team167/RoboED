package org.iowacityrobotics.roboed.data.inter;

import org.iowacityrobotics.roboed.data.IStatefulData;

/**
 * Function that interpolates two data streams.
 * @author Evan Geng
 */
@FunctionalInterface
public interface IInterpolator<I1, I2, O> extends IStatefulData {

    /**
     * Interpolates two data elements.
     * @param a Input element A.
     * @param b Input element B.
     * @return The output element.
     */
    O apply(I1 a, I2 b);

}
