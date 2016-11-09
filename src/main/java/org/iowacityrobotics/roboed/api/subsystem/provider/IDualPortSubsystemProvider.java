package org.iowacityrobotics.roboed.api.subsystem.provider;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;

/**
 * Provides subsystems that are identified by two port numbers.
 * @author Evan Geng
 */
public interface IDualPortSubsystemProvider<I, O> extends ISubsystemProvider<I, O> {

    /**
     * Instantiates a new subsystem with the given port numbers.
     * @param port1 The first port number.
     * @param port2 The second port number.
     * @return The newly instantiated subsystem.
     */
    ISubsystem<I, O> getSubsystem(int port1, int port2);
    
}
