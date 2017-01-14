package org.iowacityrobotics.roboed.util.math;

/**
 * A three-dimensional vector.
 * @author Evan Geng
 */
public class Vector3 implements Cloneable {

    /**
     * An immutable {@link Vector3} representing (0, 0, 0).
     */
    public static final Vector3 ZERO = new Vector3() {
        
        @Override
        public Vector3 x(double newX) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }
        
        @Override
        public Vector3 y(double newY) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }
        
        @Override
        public Vector3 z(double newZ) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }
         
        @Override
        public Vector3 set(double x, double y, double z) {
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
     * The z value for this vector.
     */
    private double z;
    
    /**
     * Creates a new Vector2 with the value (0, 0).
     */
    public Vector3() {
        this(0, 0, 0);
    }
    
    /**
     * Creates a new Vector2 with the given values.
     * @param x The x value.
     * @param y The y value.
     * @param z The z value.
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
     * Gets the z value.
     * @return The z value.
     */
    public double z() {
        return z;
    }
    
    /**
     * Sets the x value.
     * @param newX The new x value.
     * @return This vector, for chaining.
     */
    public Vector3 x(double newX) {
        this.x = newX;
        return this;
    }
    
    /**
     * Sets the y value.
     * @param newY The new y value.
     * @return This vector, for chaining.
     */
    public Vector3 y(double newY) {
        this.y = newY;
        return this;
    }
    
    /**
     * Sets the z value.
     * @param newZ The new z value.
     * @return This vector, for chaining.
     */
    public Vector3 z(double newZ) {
        this.z = newZ;
        return this;
    }
    
    /**
     * Sets the x and y values.
     * @param x The new x value.
     * @param y The new y value.
     * @param z The new z value.
     * @return This vector, for chaining.
     */
    public Vector3 set(double x, double y, double z) {
        return x(x).y(y).z(z);
    }
    
    /**
     * Adds another vector to this one.
     * @param x The x value to add.
     * @param y The y value to add.
     * @param z The z value to add.
     * @return This vector, for chaining.
     */
    public Vector3 add(double x, double y, double z) {
        return x(this.x + x).y(this.y + y).z(this.z + z);
    }
    
    /**
     * Adds another vector to this one.
     * @param vec The x value to add.
     * @return This vector, for chaining.
     */
    public Vector3 add(Vector3 vec) {
        return add(vec.x, vec.y, vec.z);
    }
    
    /**
     * Multiplies this vector's magnitude by a given factor.
     * @param fac The factor.
     * @return This vector, for chaining.
     */
    public Vector3 multiply(double fac) {
        return x(x * fac).y(y * fac).z(z * fac);
    }
    
    /**
     * Changes this vector to its additive inverse.
     * @return This vector, for chaining.
     */
    public Vector3 negate() {
        return multiply(-1);
    }
    
    /**
     * Gets the magnitude of this vector.
     * @return The vector's magnitude.
     */
    public double magnitude() {
        return Math.hypot(x,  Math.hypot(y, z));
    }
    
    /**
     * Normalizes the values of this vector.
     * @return This vector, for chaining.
     */
    public Vector3 normalize() {
        return multiply(Math.pow(magnitude(), -1));
    }
    
    /**
     * Creates a new 2-dimensional vector with this vector's x and y values.
     * @return The new vector.
     */
    public Vector2 truncate() {
        return new Vector2(x, y);
    }
    
    /**
     * Checks if this vector DOES equal another.
     * @param o The other vector.
     * @return Whether they're equal or not.
     */
    private boolean doesEqual(Vector3 o) {
        return x == o.x && y == o.y && z == o.z;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Vector3 && doesEqual((Vector3)o);
    }
    
    @Override
    public Vector3 clone() {
        return new Vector3(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + Double.toString(x) + ", " + Double.toString(y) + ", " + Double.toString(z) + ")";
    }
    
}
