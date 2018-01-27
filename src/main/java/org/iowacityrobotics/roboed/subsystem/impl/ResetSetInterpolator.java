package org.iowacityrobotics.roboed.subsystem.impl;

import org.iowacityrobotics.roboed.data.inter.Interpolator;

/**
 * Interpolator that implements a reset-set latch.
 * @author Evan Geng
 */
public class ResetSetInterpolator<T> extends Interpolator<Boolean, Boolean, T> {

    /**
     * The value to output while this latch is unset.
     */
    private final T off;

    /**
     * The value to output while this latch is set.
     */
    private final T on;

    /**
     * Whether this latch is currently set or not.
     */
    private boolean state = false;

    /**
     * Creates a new reset-set latch that produces the given values while unset or set.
     * @param off The value to output while unset.
     * @param on The value to output while set.
     */
    public ResetSetInterpolator(T off, T on) {
        this.off = off;
        this.on = on;
    }

    @Override
    public T apply(Boolean reset, Boolean set) {
        return (state = !reset && (set || state)) ? on : off;
    }

}
