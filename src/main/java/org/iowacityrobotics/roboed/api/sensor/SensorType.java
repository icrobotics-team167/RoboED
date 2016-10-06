package org.iowacityrobotics.roboed.api.sensor;

/**
 * Represents a type of sensor.
 * @author Evan Geng
 * @param <T> The data type accepted by this sensor.
 */
public class SensorType<T> {

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
