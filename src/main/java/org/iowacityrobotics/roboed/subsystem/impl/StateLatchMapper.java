package org.iowacityrobotics.roboed.subsystem.impl;

import org.iowacityrobotics.roboed.data.mapper.Mapper;

/**
 * A latch that stores and outputs a "non-empty" state.
 * @author Evan Geng
 */
public class StateLatchMapper<T> extends Mapper<T, T> {

    /**
     * The "empty" state. Represents the lack of a meaningful state.
     */
    private final T empty;

    /**
     * The currently stored state.
     */
    private T state;

    /**
     * Creates a new latch.
     * @param empty The "empty" state. Represents the lack of a meaningful state.
     * @param initial The initial stored state.
     */
    public StateLatchMapper(T empty, T initial) {
        this.empty = empty;
        this.state = initial;
    }

    @Override
    public T apply(T data) {
        return data == empty ? state : (state = data);
    }

}
