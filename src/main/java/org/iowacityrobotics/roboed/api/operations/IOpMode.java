package org.iowacityrobotics.roboed.api.operations;

import org.iowacityrobotics.roboed.util.function.ICondition;
import org.iowacityrobotics.roboed.util.function.IConditionFactory;

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
     * Sets a function to execute while this opmode is in effect. Execution continues while the condition is met.
     * @param factory The factory to generate the function.
     * @return This opmode instance.
     */
    IOpMode whileCondition(IConditionFactory factory);

    /**
     * Sets a function to execute while this opmode is in effect. Execution continues while the condition is met.
     * @param executor The function.
     * @return This opmode instance.
     */
    default IOpMode whileCondition(ICondition executor) {
        return whileCondition(IConditionFactory.getter(executor));
    }

    /**
     * Sets a function to execute while this opmode is in effect. Execution continues until the condition is met.
     * @param factory The factory to generate the function.
     * @return This opmode instance.
     */
    default IOpMode untilCondition(IConditionFactory factory) {
        return whileCondition(factory.negate());
    }

    /**
     * Sets a function to execute while this opmode is in effect. Execution continues until the condition is met.
     * @param executor The function.
     * @return This opmode instance.
     */
    default IOpMode untilCondition(ICondition executor) {
        return whileCondition(executor.negate());
    }

    /**
     * Sets a function to execute upon this opmode's completion.
     * @param executor The function.
     * @return This opmode instance.
     */
    IOpMode onDone(Runnable executor);
    
}
