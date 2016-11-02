package org.iowacityrobotics.roboed.api.operations;

/**
 * Represents an operational mode for the robot.
 * @author Evan Geng
 */
public interface IOpMode {
    
    /**
     * Sets this operational mode's execution.
     * @param executor The execution.
     */
    void setExecutor(Runnable executor);

    /**
     * Sets this operational mode as the current mode.
     */
    void setActive();
    
}
