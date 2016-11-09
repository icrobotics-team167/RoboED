package org.iowacityrobotics.roboed.api.subsystem;

import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;

/**
 * A registry for the robot's subsystems.
 * @author Evan Geng
 */
public interface ISystemRegistry {

    /**
     * Get a particular subsystem.
     * @param type The subsystem's type.
     * @param id The subsystem's ID.
     * @return The requested subsystem, if it exists.
     */
    <I, O, P extends ISubsystemProvider<I, O>> ISubsystem<I, O> getSubsystem(ISubsystemType<I, O, P> type, int id);
    
    /**
     * Gets a provider for the given subsystem type.
     * @param type The subsystem type.
     * @return The provider for this subsystem type.
     */
    <I, O, P extends ISubsystemProvider<I, O>> P getProvider(ISubsystemType<I, O, P> type);
    
}
