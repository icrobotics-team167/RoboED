package org.iowacityrobotics.roboed.subsystem.impl;

import org.iowacityrobotics.roboed.data.mapper.Mapper;

/**
 * An implementation of a counter that outputs a different value each time input rises from false to true.
 * @author Evan Geng
 */
public class CounterMapper<T> extends Mapper<Boolean, T> {

    /**
     * The initial output value.
     */
    private final T initial;

    /**
     * Subsequent output values.
     */
    private final T[] values;

    /**
     * The index of the current output value.
     */
    private int index;

    /**
     * The input value from the last frame.
     */
    private boolean lastFrame;

    /**
     * Creates a new counter that outputs the given values.
     * @param initial The initial output value.
     * @param values The subsequent output values.
     */
    public CounterMapper(T initial, T[] values) {
        this.initial = initial;
        this.values = values;
        this.index = 0;
    }

    @Override
    public T apply(Boolean data) {
        if (!lastFrame && data && index < values.length) index++;
        lastFrame = data;
        return index > 0 ? values[index - 1] : initial;
    }

}
