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
    UNINITIALIZED(false),
    
    /**
     * The robot is disabled.
     */
    DISABLED(false),
    
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
     * Whether data sinks should be ticked in this mode or not.
     */
    public final boolean shouldTick;

    /**
     * The operation to run on mode change.
     */
    private Runnable operation;

    /**
     * Constructs a robot mode.
     * @param shouldTick Whether data sinks should be ticked in this mode or not.
     */
    RobotMode(boolean shouldTick) {
        this.shouldTick = shouldTick;
    }

    /**
     * Constructs a robot mode under the assumption that data sinks should be ticked.
     */
    RobotMode() {
        this(true);
    }

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
     * Gets the robot's current operational mode as seen in real time.
     * @return The robot's real mode.
     */
    public static RobotMode getUnsafe() {
        return Robot.runMode;
    }

    /**
     * Gets the robot's operational mode as seen by the current thread.
     * @return The robot's canonical mode.
     */
    public static RobotMode get() {
        RobotMode mode = Flow.threadMode.get();
        return mode == null ? getUnsafe() : mode;
    }
    
}
