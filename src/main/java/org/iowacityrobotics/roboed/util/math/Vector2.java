package org.iowacityrobotics.roboed.util.math;

/**
 * A vector of two numbers.
 * @author Evan Geng
 */
public class Vector2 implements Cloneable {

    /**
     * An immutable {@link Vector2} representing (0, 0).
     */
    public static final Vector2 ZERO = new Vector2() {
        
        @Override
        public Vector2 x(double newX) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }
        
        @Override
        public Vector2 y(double newY) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }
        
        @Override
        public Vector2 set(double x, double y) {
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
     * Creates a new Vector2 with the value (0, 0).
     */
    public Vector2() {
        this(0, 0);
    }
    
    /**
     * Creates a new Vector2 with the given values.
     * @param x The x value.
     * @param y The y value.
     */
    public Vector2(double x, double y) {
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
    public Vector2 x(double newX) {
        this.x = newX;
        return this;
    }
    
    /**
     * Sets the y value.
     * @param newY The new y value.
     * @return This vector, for chaining.
     */
    public Vector2 y(double newY) {
        this.y = newY;
        return this;
    }
    
    /**
     * Sets the x and y values.
     * @param x The new x value.
     * @param y The new y value.
     * @return This vector, for chaining.
     */
    public Vector2 set(double x, double y) {
        return x(x).y(y);
    }
    
    /**
     * Adds another vector to this one.
     * @param x The x value to add.
     * @param y The y value to add.
     * @return This vector, for chaining.
     */
    public Vector2 add(double x, double y) {
        return x(this.x + x).y(this.y + y);
    }
    
    /**
     * Adds another vector to this one.
     * @param vec The other vector.
     * @return This vector, for chaining.
     */
    public Vector2 add(Vector2 vec) {
        return add(vec.x, vec.y);
    }
    
    /**
     * Multiplies this vector's magnitude by a given factor.
     * @param fac The factor.
     * @return This vector, for chaining.
     */
    public Vector2 multiply(double fac) {
        return x(x * fac).y(y * fac);
    }
    
    /**
     * Changes this vector to its additive inverse.
     * @return This vector, for chaining.
     */
    public Vector2 negate() {
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
    public Vector2 normalize() {
        return multiply(Math.pow(magnitude(), -1));
    }
    
    /**
     * Creates a new 3-dimensional vector with this vector's x and y values.
     * @param z The new vector's z value.
     * @return The new vector.
     */
    public Vector3 augment(double z) {
        return new Vector3(x, y, z);
    }
    
    /**
     * Checks if this vector DOES equal another.
     * @param o The other vector.
     * @return Whether they're equal or not.
     */
    private boolean doesEqual(Vector2 o) {
        return x == o.x && y == o.y;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Vector2 && doesEqual((Vector2)o);
    }
    
    @Override
    public Vector2 clone() {
        return new Vector2(x, y);
    }

    @Override
    public String toString() {
        return "(" + Double.toString(x) + ", " + Double.toString(y) + ")";
    }
    
    /**
     * Creates a new vector from the given polar coordinates.
     * @param angle The angle of the vector, in radians.
     * @param magnitude The magnitude of the vector.
     * @return The new vector.
     */
    public static Vector2 fromPolar(double angle, double magnitude) {
        return new Vector2(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
    }
    
}
