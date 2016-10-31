package org.iowacityrobotics.roboed.api;

/**
 * Interface representing the robot instances. Exposes all the necessary API functions to program a functioning robot.
 * @author Evan Geng
 */
public interface IRobot {
    
    /**
     * Gets the operational mode of the robot.
     * @return The robot's operational mode.
     */
    RobotMode mode();
    
}
