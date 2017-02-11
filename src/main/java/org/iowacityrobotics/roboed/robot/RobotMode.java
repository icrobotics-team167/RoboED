package org.iowacityrobotics.roboed.robot;

/**
 * Constants representing the robot's operational modes.
 * @author Evan Geng
 */
public enum RobotMode {

    /**
     * The robot hasn't been initialized yet.
     */
    UNINITIALIZED,
    
    /**
     * The robot is disabled.
     */
    DISABLED,
    
    /**
     * THe robot is in autonomous mode.
     */
    AUTO,
    
    /**
     * The robot is in teleoperated mode.
     */
    TELEOP,
    
    /**
     * The robot is in testing mode.
     */
    TEST
    
}
