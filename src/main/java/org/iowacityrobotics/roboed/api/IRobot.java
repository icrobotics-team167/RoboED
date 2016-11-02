package org.iowacityrobotics.roboed.api;

import org.iowacityrobotics.roboed.api.operations.IOperationsManager;
import org.iowacityrobotics.roboed.api.subsystem.ISystemRegistry;

/**
 * Interface representing the robot instances. Exposes all the necessary API functions to program a functioning robot.
 * @author Evan Geng
 */
public interface IRobot {
    
    /**
     * Gets the system registry for this robot.
     * @return The robot's system registry.
     */
    ISystemRegistry getSystemRegistry();
    
    /**
     * Gets the operations manager for this robot.
     * @return The robot's operations manager.
     */
    IOperationsManager getOpManager();
    
    /**
     * Gets the running mode of the robot.
     * @return The robot's running mode.
     */
    RobotMode getRunningMode();
    
}
