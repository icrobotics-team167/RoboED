package org.iowacityrobotics.roboed.api.actuator;

/**
 * Tracks {@link IActuator}s and updates them as needed.
 * @author Evan Geng
 */
public interface IActuatorRegistry {

    /**
     * Registers a new actuator with the given port and type.
     * @param port The device's port number.
     * @param type The type of device to register.
     * @param <T> The type of data accepted by the device.
     * @return The newly registered device.
     */
    <T> IActuator<T> put(int port, ActuatorType<T> type);

    /**
     * Gets an actuator on the given port.
     * @param id The device's port number.
     * @param <T> The type of data accepted by the device.
     * @return The device found, if one exists. Otherwise, returns null.
     */
    <T> IActuator<T> get(int id);
    
}
