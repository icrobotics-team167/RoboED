package org.iowacityrobotics.roboed.robot;

import org.iowacityrobotics.roboed.util.function.Lambdas;

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
    TEST;

    /**
     * The operation to run on mode change.
     */
    private Runnable operation;

    /**
     * Gets the mode change operation.
     * @return The operation to run on mode change.
     */
    public Runnable getOperation() {
        return operation != null ? operation : Lambdas.noopNullary();
    }

    /**
     * Sets the operation for this robot mode.
     * @param op The operation to run on mode change.
     */
    public void setOperation(Runnable op) {
        this.operation = op;
    }

    /**
     * Gets the robot's current operational mode.
     * @return The robot's mode.
     */
    public static RobotMode get() {
        return Robot.runMode;
    }
    
}
