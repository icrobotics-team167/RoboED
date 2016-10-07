package org.iowacityrobotics.roboed.api.sensor;

import org.iowacityrobotics.roboed.util.math.Vector2;

/**
 * Represents a type of sensor.
 * @author Evan Geng
 * @param <T> The data type accepted by this sensor.
 */
public class SensorType<T> {

    /**
     * Represents a simple analog sensor of some unknown type.
     */
    public static final SensorType<Double> ANALOG = new SensorType<>("s_analog");

    /**
     * Represents a joystick. The port number should be equal to <code>controllerId | (xAxisId &lt;&lt; 4) | (yAxisId &lt;&lt; 8)</code>.
     */
    public static final SensorType<Vector2> JOYSTICK = new SensorType<>("s_joystick");

    /**
     * Represents a simple digital sensor of some unknown type.
     */
    public static final SensorType<Boolean> DIGITAL = new SensorType<>("s_digital");

    /**
     * Represents a limit switch.
     */
    public static final SensorType<Boolean> LIMIT = new SensorType<>("s_limit");

    /**
     * Represents a button. The port number should be equal to <code>controller | (buttonId &lt;&lt; 4)</code>.
     */
    public static final SensorType<Boolean> BUTTON = new SensorType<>("s_button");

    /**
     * The name of this device.
     */
    private final String name;
    
    /**
     * Creates a new ActuatorType with the given name.
     * @param name The actuator's name.
     */
    public SensorType(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name of this device.
     * @return The device name.
     */
    public String name() {
        return name;
    }
    
}
