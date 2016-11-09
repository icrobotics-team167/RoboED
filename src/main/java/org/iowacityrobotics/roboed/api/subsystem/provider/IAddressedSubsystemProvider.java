package org.iowacityrobotics.roboed.api.subsystem.provider;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;

/**
 * Provides subsystems that are identified by a 8-bit (or less) address.
 * @author Evan Geng
 */
public interface IAddressedSubsystemProvider<I, O> extends ISubsystemProvider<I, O> {

    /**
     * Instantiates a new subsystem with the given address.
     * @param address The subsystem's address.
     * @return The newly instantiated subsystem.
     */
    ISubsystem<I, O> getSubsystem(byte address);
    
}