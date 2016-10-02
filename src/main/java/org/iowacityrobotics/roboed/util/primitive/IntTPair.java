package org.iowacityrobotics.roboed.util.primitive;

/**
 * An immutable 2-tuple (i.e. pair) of the primitive <code>int</code> and some other object.
 * @param <T> The type of object B.
 * @author Evan Geng
 */
public class IntTPair<T> {

    /**
     * Creates a new pair from the given values.
     * @param a The integer A.
     * @param b Object B.
     * @param <T> Object B's type.
     * @return The newly created pair.
     */
    public static <T> IntTPair<T> of(int a, T b) {
        return new IntTPair<>(a, b);
    }

    /**
     * Value A.
     */
    private final int a;

    /**
     * Object B.
     */
    private final T b;

    /**
     * Creates a new pair from the given values.
     * @param a The integer A.
     * @param b Object B.
     */
    public IntTPair(int a, T b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Gets value A, as an integer.
     * @return Integer A.
     */
    public int getA() {
        return a;
    }

    /**
     * Gets object B.
     * @return Object B.
     */
    public T getB() {
        return b;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object o) {
        return o instanceof IntTPair && ((IntTPair)o).a == a && ((IntTPair)o).b.equals(b);
    }
    
    @Override
    public String toString() {
        return new StringBuilder("(").append(a).append(", ").append(b.toString()).append(")").toString();
    }

}
