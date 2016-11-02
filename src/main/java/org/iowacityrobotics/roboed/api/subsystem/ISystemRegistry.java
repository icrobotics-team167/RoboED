package org.iowacityrobotics.roboed.api.subsystem;

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
    <I, O> ISubsystem<I, O> getSubsystem(ISubsystemType<I, O> type, int id);

    /**
     * Registers a subsystem.
     * @param system The subsystem to register.
     * @return
     */
    <I, O> int registerSubsystem(ISubsystem<I, O> system);
    
}
