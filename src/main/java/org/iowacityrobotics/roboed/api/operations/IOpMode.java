package org.iowacityrobotics.roboed.api.operations;

import java.util.function.BooleanSupplier;

/**
 * Represents an operational mode for the robot.
 * @author Evan Geng
 */
public interface IOpMode {

    /**
     * Marks this opmode as temporary, meaning it should return to the previous opmode once it completes.
     * @return This opmode instance.
     */
    IOpMode setTemporary();

    /**
     * Sets the opmode to switch to upon this opmode's completion.
     * @param id The new opmode.
     * @return This opmode instance.
     */
    IOpMode setNext(String id);

    /**
     * Sets a function to execute upon this opmode's initialization.
     * @param executor The function.
     * @return This opmode instance.
     */
    IOpMode onInit(Runnable executor);

    /**
     * Adds a function to execute in sequence while this opmode is in effect.
     * @param executor The function.
     * @return This opmode instance.
     */
    IOpMode then(Runnable executor);

    /**
     * Adds an instruction to change to the specified opmode.
     * @param id The new opmode.
     * @return This opmode instance.
     */
    IOpMode thenSwitch(String id);

    /**
     * Adds a conditional instruction that skips the next instruction if the condition isn't met.
     * @param condition The condition.
     * @return This opmode instance.
     */
    IOpMode ifCondition(BooleanSupplier condition);

    /**
     * Adds an instruction to skip the next n instructions.
     * @param n The number of instructions to skip.
     * @return This opmode instance.
     */
    IOpMode thenSkip(int n);

    /**
     * Sets a function to execute upon this opmode's completion.
     * @param executor The function.
     * @return This opmode instance.
     */
    IOpMode onDone(Runnable executor);
    
}
