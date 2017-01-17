package org.iowacityrobotics.roboed.api.subsystem.provider;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;

/**
 * Provides subsystems that are identified by a single port number.
 * @author Evan Geng
 */
public interface ISinglePortSubsystemProvider<I, O> extends ISubsystemProvider<I, O> {

    /**
     * Instantiates a new subsystem with the given port number.
     * @param port The port number.
     * @return The newly instantiated subsystem.
     */
    @Providing
    ISubsystem<I, O> getSubsystem(int port);
    
}
