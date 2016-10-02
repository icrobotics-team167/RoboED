package org.iowacityrobotics.roboed.api.sensor;

/**
 * Tracks {@link ISensor}s and updates them as needed.
 * @author Evan Geng
 */
public interface ISensorRegistry {

    /**
     * Registers a new sensor with the given port and type.
     * @param id The device's port number.
     * @param type The type of device to register.
     * @param <T> The type of data supplied by the device.
     * @return The newly registered device.
     */
    <T> ISensor<T> put(int id, String type);

    /**
     * Gets an sensor on the given port.
     * @param id The device's port number.
     * @param <T> The type of data supplied by the device.
     * @return The device found, if one exists. Otherwise, returns null.
     */
    <T> ISensor<T> get(int id);
    
}
