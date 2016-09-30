package org.iowacityrobotics.roboed.util.primitive;

public class IntTPair<T> {

	public static <T> IntTPair<T> of(int a, T b) {
        return new IntTPair<>(a, b);
    }
    
    private final int a;
    private final T b;
    
    public IntTPair(int a, T b) {
        this.a = a;
        this.b = b;
    }
    
    public int getA() {
        return a;
    }
    
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
