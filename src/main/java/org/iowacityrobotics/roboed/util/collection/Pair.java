package org.iowacityrobotics.roboed.util.collection;

/**
 * Represents an immutable 2-tuple (i.e. pair of objects).
 * @param <A> The type of object A.
 * @param <B> The type of object B.
 * @author Evan Geng
 */
public class Pair<A, B> {

    /**
     * Creates a new pair of the two given objects.
     * @param a Object A.
     * @param b Object B.
     * @param <A> Object A's type.
     * @param <B> Object B's type.
     * @return The newly created pair.
     */
    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    /**
     * Object A of this pair.
     */
    private final A a;

    /**
     * Object B of this pair.
     */
    private final B b;

    /**
     * Creates a new pair of the two given objects.
     * @param a Object A.
     * @param b Object B.
     */
    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Gets object A.
     * @return Object A.
     */
    public A getA() {
        return a;
    }

    /**
     * Gets object B.
     * @return Object B.
     */
    public B getB() {
        return b;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object o) {
        return o instanceof Pair && ((Pair)o).a.equals(a) && ((Pair)o).b.equals(b);
    }
    
    @Override
    public String toString() {
        return "(" + a.toString() + ", " + b.toString() + ")";
    }

}
