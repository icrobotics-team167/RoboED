package org.iowacityrobotics.roboed.util.math;

/**
 * A three-dimensional vector.
 * @author Evan Geng
 */
public class Vector4 implements Cloneable {

    /**
     * An immutable {@link Vector4} representing (0, 0, 00, ).
     */
    public static final Vector4 ZERO = new Vector4() {

        @Override
        public Vector4 x(double newX) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }

        @Override
        public Vector4 y(double newY) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }

        @Override
        public Vector4 z(double newZ) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }

        @Override
        public Vector4 w(double newW) {
            throw new UnsupportedOperationException("Cannot modify immutable!");
        }

        @Override
        public Vector4 set(double x, double y, double z, double w) {
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
     * The w value for this vector.
     */
    private double w;

    /**
     * Creates a new Vector4 with the value (0, 0, 0, 0).
     */
    public Vector4() {
        this(0, 0, 0, 0);
    }

    /**
     * Creates a new Vector2 with the given values.
     * @param x The x value.
     * @param y The y value.
     * @param z The z value.
     * @param w The w value.
     */
    public Vector4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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
     * Gets the w value.
     * @return The w value.
     */
    public double w() {
        return w;
    }
    
    /**
     * Sets the x value.
     * @param newX The new x value.
     * @return This vector, for chaining.
     */
    public Vector4 x(double newX) {
        this.x = newX;
        return this;
    }
    
    /**
     * Sets the y value.
     * @param newY The new y value.
     * @return This vector, for chaining.
     */
    public Vector4 y(double newY) {
        this.y = newY;
        return this;
    }
    
    /**
     * Sets the z value.
     * @param newZ The new z value.
     * @return This vector, for chaining.
     */
    public Vector4 z(double newZ) {
        this.z = newZ;
        return this;
    }

    /**
     * Sets the w value.
     * @param newW The new w value.
     * @return This vector, for chaining.
     */
    public Vector4 w(double newW) {
        this.w = newW;
        return this;
    }
    
    /**
     * Sets the x, y, z, and w values.
     * @param x The new x value.
     * @param y The new y value.
     * @param z The new z value.
     * @param w The new w value.
     * @return This vector, for chaining.
     */
    public Vector4 set(double x, double y, double z, double w) {
        return x(x).y(y).z(z).w(w);
    }
    
    /**
     * Adds another vector to this one.
     * @param x The x value to add.
     * @param y The y value to add.
     * @param z The z value to add.
     * @param w The w value to add.
     * @return This vector, for chaining.
     */
    public Vector4 add(double x, double y, double z, double w) {
        return x(this.x + x).y(this.y + y).z(this.z + z).w(this.w + w);
    }
    
    /**
     * Adds another vector to this one.
     * @param vec The other vector.
     * @return This vector, for chaining.
     */
    public Vector4 add(Vector4 vec) {
        return add(vec.x, vec.y, vec.z, vec.w);
    }

    /**
     * Multiplies this vector's first two dimensions by a given factor.
     * @param fac The factor.
     * @return This vector, for chaining.
     */
    public Vector4 multiply2D(double fac) {
        return x(x * fac).y(y * fac).z(z).w(w);
    }
    
    /**
     * Multiplies this vector's magnitude by a given factor.
     * @param fac The factor.
     * @return This vector, for chaining.
     */
    public Vector4 multiply(double fac) {
        return x(x * fac).y(y * fac).z(z * fac).w(w * fac);
    }
    
    /**
     * Changes this vector to its additive inverse.
     * @return This vector, for chaining.
     */
    public Vector4 negate() {
        return multiply(-1);
    }
    
    /**
     * Gets the magnitude of this vector.
     * @return The vector's magnitude.
     */
    public double magnitude() {
        return Math.hypot(x,  Math.hypot(y, Math.hypot(z, w)));
    }
    
    /**
     * Normalizes the values of this vector.
     * @return This vector, for chaining.
     */
    public Vector4 normalize() {
        return multiply(Math.pow(magnitude(), -1));
    }

    /**
     * Creates a new 3-dimensional vector with this vector's x, y, and z values.
     * @return The new vector.
     */
    public Vector3 truncate() {
        return new Vector3(x, y, z);
    }
    
    /**
     * Creates a new 2-dimensional vector with this vector's x and y values.
     * @return The new vector.
     */
    public Vector2 truncate2() {
        return new Vector2(x, y);
    }
    
    /**
     * Checks if this vector DOES equal another.
     * @param o The other vector.
     * @return Whether they're equal or not.
     */
    private boolean doesEqual(Vector4 o) {
        return x == o.x && y == o.y && z == o.z && w == o.w;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Vector4 && doesEqual((Vector4)o);
    }
    
    @Override
    public Vector4 clone() {
        return new Vector4(x, y, z, w);
    }

    @Override
    public String toString() {
        return "(" + Double.toString(x) + ", " + Double.toString(y) + ", " + Double.toString(z) + ", " + Double.toString(w) + ")";
    }
    
}
