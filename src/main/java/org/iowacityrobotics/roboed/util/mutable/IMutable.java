package org.iowacityrobotics.roboed.util.mutable;

/**
 * Represents a mutability wrapper for a type.
 * @author Evan Geng
 * @param <T> The wrapped type.
 */
public interface IMutable<T> {

    /**
     * Gets the wrapped object.
     * @return The wrapped object.
     */
    T get();
    
    /**
     * Sets the wrapped object.
     * @param value The new value.
     */
    void set(T value);
    
    default boolean isNull() {
        return get() == null;
    }
    
}
