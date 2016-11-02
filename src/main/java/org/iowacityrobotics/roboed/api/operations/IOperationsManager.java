package org.iowacityrobotics.roboed.api.operations;

import org.iowacityrobotics.roboed.api.RobotMode;

/**
 * Manages operational modes and drives data processing.
 * @author Evan Geng
 */
public interface IOperationsManager {

    /**
     * Get a particular operational mode, or creates it if it doesn't exist.
     * @param id The ID of the mode.
     * @return The requested operational mode.
     */
    IOpMode getOpMode(String id);
    
    /**
     * Sets the default operational mode for the given robot state.
     * @param status The robot state.
     * @param id The operational mode to run by default in this state.
     */
    void setDefaultOpMode(RobotMode status, String id);

    /**
     * Sets the current operational mode.
     * @param id The new operational mode.
     */
    void setOpMode(String id);
    
}
