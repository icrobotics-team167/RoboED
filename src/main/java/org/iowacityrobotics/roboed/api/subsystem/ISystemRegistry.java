package org.iowacityrobotics.roboed.api.subsystem;

import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;

/**
 * A registry for the robot's subsystems.
 * @author Evan Geng
 */
public interface ISystemRegistry {
    
    /**
     * Gets a provider for the given subsystem type.
     * @param type The subsystem type.
     * @return The provider for this subsystem type.
     */
    <I, O, P extends ISubsystemProvider<I, O>> P getProvider(ISubsystemType<I, O, P> type);

    /**
     * Registers a new custom subsystem provider for the given subsystem type.
     * @param type The subsystem type provided by the provider.
     * @param provider The new custom provider.
     */
    <I, O, P extends ISubsystemProvider<I, O>> void registerProvider(ISubsystemType<I, O, P> type, P provider);
    
}
