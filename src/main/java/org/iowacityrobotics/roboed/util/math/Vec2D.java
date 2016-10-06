package org.iowacityrobotics.roboed.util.math;

/**
 * A vector of two numbers.
 * @author Evan Geng
 */
public class Vec2D implements Cloneable {

    /**
     * An immutable {@link Vec2D} representing (0, 0).
     */
    public static final Vec2D ZERO = new Vec2D() {
        
        @Override
        public Vec2D x(double newX) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }
        
        @Override
        public Vec2D y(double newY) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }
        
        @Override
        public Vec2D set(double x, double y) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }
        
    };
    
    /**
     * The x value for this vector.
     */
    private double x;
    
    /**
     * The y value for this vector.
     */
    private double y;
    
    /**
     * Creates a new Vec2D with the value (0, 0).
     */
    public Vec2D() {
        this(0, 0);
    }
    
    /**
     * Creates a new Vec2D with the given values.
     * @param x The x value.
     * @param y The y value.
     */
    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Gets the x value.
     * @return The x value.
     */
    public double x() {
        return x;
    }
    
    /**
     * Gets the y value.
     * @return The y value.
     */
    public double y() {
        return y;
    }
    
    /**
     * Sets the x value.
     * @param newX The new x value.
     * @return This vector, for chaining.
     */
    public Vec2D x(double newX) {
        this.x = newX;
        return this;
    }
    
    /**
     * Sets the y value.
     * @param newY The new y value.
     * @return This vector, for chaining.
     */
    public Vec2D y(double newY) {
        this.y = newY;
        return this;
    }
    
    /**
     * Sets the x and y values.
     * @param x The new x value.
     * @param y The new y value.
     * @return This vector, for chaining.
     */
    public Vec2D set(double x, double y) {
        return x(x).y(y);
    }
    
    /**
     * Adds another vector to this one.
     * @param x The x value to add.
     * @param y The y value to add.
     * @return This vector, for chaining.
     */
    public Vec2D add(double x, double y) {
        return x(this.x + x).y(this.y + y);
    }
    
    /**
     * Adds another vector to this one.
     * @param vec The x value to add.
     * @return This vector, for chaining.
     */
    public Vec2D add(Vec2D vec) {
        return add(vec.x, vec.y);
    }
    
    /**
     * Multiplies this vector's magnitude by a given factor.
     * @param fac The factor.
     * @return This vector, for chaining.
     */
    public Vec2D multiply(double fac) {
        return x(x * fac).y(y * fac);
    }
    
    /**
     * Changes this vector to its additive inverse.
     * @return This vector, for chaining.
     */
    public Vec2D negate() {
        return multiply(-1);
    }
    
    /**
     * Gets the magnitude of this vector.
     * @return The vector's magnitude.
     */
    public double magnitude() {
        return Math.hypot(x, y);
    }
    
    /**
     * Normalizes the values of this vector.
     * @return This vector, for chaining.
     */
    public Vec2D normalize() {
        double fac = Math.pow(magnitude(), -1);
        return x(x * fac).y(y * fac);
    }
    
    /**
     * Checks if this vector DOES equal another.
     * @param o The other vector.
     * @return Whether they're equal or not.
     */
    private boolean doesEqual(Vec2D o) {
        return x == o.x && y == o.y;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Vec2D && doesEqual((Vec2D)o);
    }
    
    @Override
    public Vec2D clone() {
        return new Vec2D(x, y);
    }
    
}
