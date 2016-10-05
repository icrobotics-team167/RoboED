package org.iowacityrobotics.roboed.util.mutable;

/**
 * A mutability wrapper for an object.
 * @author Evan Geng
 * @param <T> The type being wrapped.
 */
public class MutableObject<T> implements IMutable<T> {

    /**
     * The wrapped object.
     */
    private T value;
    
    /**
     * Creates a new MutableObject without a value.
     */
    public MutableObject() {
        // NO-OP
    }
    
    /**
     * Creates a new MutableBoolean with a value.
     * @param value The initial value.
     */
    public MutableObject(T value) {
        this.value = value;
    }
    
    @Override
    public T get() {
        return value;
    }
    
    @Override
    public void set(T value) {
        this.value = value;
    }
    
}
