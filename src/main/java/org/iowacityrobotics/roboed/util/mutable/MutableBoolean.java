package org.iowacityrobotics.roboed.util.mutable;

/**
 * A mutable wrapper for the primitive <code>boolean</code>.
 * @author Evan Geng
 */
public class MutableBoolean implements IMutable<Boolean> {

    /**
     * The value.
     */
    private boolean value;
    
    /**
     * Creates a new MutableBoolean with a value of <code>false</code>.
     */
    public MutableBoolean() {
        this(false);
    }
    
    /**
     * Creates a new MutableObject with a value.
     * @param value The initial value.
     */
    public MutableBoolean(boolean value) {
        this.value = value;
    }
    
    /**
     * The counterpart to {@link #get()} that operates on the primitive <code>boolean</code>.
     * @return Gets the underlying value for this object.
     */
    public boolean getPrim() {
        return value;
    }
    
    /**
     * The counterpart to {@link #set(Boolean)} that operates on the primitive <code>boolean</code>.
     * @param value The new underlying value.
     */
    public void setPrim(boolean value) {
        this.value = value;
    }
    
    @Override
    public Boolean get() {
        return getPrim();
    }

    @Override
    public void set(Boolean value) {
        setPrim(value);
    }

}
