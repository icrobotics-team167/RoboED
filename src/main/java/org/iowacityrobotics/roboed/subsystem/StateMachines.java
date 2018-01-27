package org.iowacityrobotics.roboed.subsystem;

import org.iowacityrobotics.roboed.data.inter.Interpolator;
import org.iowacityrobotics.roboed.subsystem.impl.ResetSetInterpolator;

/**
 * Various simple stateful pipeline node implementations.
 * @author Evan Geng
 */
public class StateMachines {

    /**
     * Creates a new reset-set latch that outputs distinct constant value while set or unset.
     * Order of data matters! The left input resets the latch and the right input sets it.
     * @param off The value to output while unset.
     * @param on The value to output while set.
     * @param <T> The type of value to be output.
     * @return The newly-created reset-set latch.
     */
    public static <T> Interpolator<Boolean, Boolean, T> rsLatch(T off, T on) {
        return new ResetSetInterpolator<>(off, on);
    }

}