package org.iowacityrobotics.roboed.vision;

/**
 * Enumeration of various different kinds of cameras.
 * @author Evan Geng
 */
public class CameraType<T> {

    /**
     * Represents a USB camera.
     */
    public static final CameraType<Integer> USB = new CameraType<>();

    /**
     * Represents an axis IP camera.
     */
    public static final CameraType<String> AXIS = new CameraType<>();

    /**
     * Creates a new camera type constant.
     */
    private CameraType() {
        // NO-OP
    }

}