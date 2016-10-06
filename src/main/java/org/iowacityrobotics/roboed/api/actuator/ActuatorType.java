package org.iowacityrobotics.roboed.api.actuator;

/**
 * Represents a type of actuator.
 * @author Evan Geng
 * @param <T> The data type accepted by this actuator.
 */
public class ActuatorType<T> {

    /**
     * The name of this device.
     */
    private final String name;
    
    /**
     * Creates a new ActuatorType with the given name.
     * @param name The actuator's name.
     */
    public ActuatorType(String name) {
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
