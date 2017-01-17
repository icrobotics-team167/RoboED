package org.iowacityrobotics.roboed.api.subsystem.provider;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;

/**
 * Provides subsystems that can't be described using the other interfaces.
 * @author Evan Geng
 */
public interface IGenericSubsystemProvider<I, O, T> extends ISubsystemProvider<I, O> {

    /**
     * Instantiates a subsystem with the given descriptor.
     * @param value The descriptor for the subsystem.
     * @return The newly instantiated subsystem.
     */
    @Providing
    ISubsystem<I, O> getSubsystem(T value);
    
}
