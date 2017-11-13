package org.iowacityrobotics.roboed.util.math;

/**
 * Represents spacial axes.
 * @author Evan Geng
 */
public enum Axis {

    /**
     * The X-axis.
     */
    X(new Vector3(1, 0, 0)),

    /**
     * The Y-axis.
     */
    Y(new Vector3(0, 1, 0)),

    /**
     * The Z-axis.
     */
    Z(new Vector3(0, 0, 1));

    /**
     * The vector offset for moving one unit in this direction.
     */
    private final Vector3 offset;

    /**
     * Creates an enum value for a spacial axis.
     * @param offset The vector offset for moving one unit in this direction.
     */
    Axis(Vector3 offset) {
        this.offset = offset;
    }

    /**
     * Retrieves the 3-dimensional normalized vector for this axis.
     * @return The offset.
     */
    public Vector3 offset3() {
        return offset.clone();
    }

    /**
     * Retrieves the 2-dimensional normalized vector for this axis.
     * @return The offset.
     */
    public Vector2 offset2() {
        return offset.truncate();
    }

}
