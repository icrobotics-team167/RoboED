package org.iowacityrobotics.roboed.util.mutable;

/**
 * A mutable wrapper for the primitive <code>int</code>.
 * @author Evan Geng
 */
public class MutableInt implements IMutable<Integer> {

    /**
     * The value.
     */
    private int value;
    
    /**
     * Creates a new MutableInt with a value of <code>0</code>.
     */
    public MutableInt() {
        this(0);
    }
    
    /**
     * Creates a new MutableInt with a value.
     * @param value The initial value.
     */
    public MutableInt(int value) {
        this.value = value;
    }
    
    /**
     * The counterpart to {@link #get()} that operates on the primitive <code>int</code>.
     * @return Gets the underlying value for this object.
     */
    public int getPrim() {
        return value;
    }
    
    /**
     * The counterpart to {@link #set(Integer)} that operates on the primitive <code>int</code>.
     * @param value The new underlying value.
     */
    public void setPrim(int value) {
        this.value = value;
    }
    
    @Override
    public Integer get() {
        return getPrim();
    }

    @Override
    public void set(Integer value) {
        setPrim(value);
    }

}
