package org.iowacityrobotics.roboed.util.mutable;

/**
 * Utility class for creating {@link IMutable}s.
 * @author Evan Geng
 */
public class Mutables {

    /**
     * Wraps an integer.
     * @param value The initial value.
     * @return The new {@link MutableInt}.
     */
    public MutableInt of(int value) {
        return new MutableInt(value);
    }
    
    /**
     * Wraps a boolean.
     * @param value The initial value.
     * @return The new {@link MutableBoolean}.
     */
    public MutableBoolean of(boolean value) {
        return new MutableBoolean(value);
    }

    /**
     * Wraps an object.
     * @param value The initial value.
     * @return The new {@link IMutable}.
     */
    public <T> IMutable<T> of(T value) {
        return new MutableObject<>(value);
    }
    
}
