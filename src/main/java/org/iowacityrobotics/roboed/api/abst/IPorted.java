package org.iowacityrobotics.roboed.api.abst;

/**
 * Represents an object that has a port number (e.g. a motor).
 * @author Evan Geng
 */
public interface IPorted {

    /**
     * Gets the port number for this device.
     * @return The port number.
     */
    int port();
    
}
