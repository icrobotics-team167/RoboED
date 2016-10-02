package org.iowacityrobotics.roboed.api.auto;

import org.iowacityrobotics.roboed.util.function.ICondition;

import java.util.concurrent.TimeUnit;

/**
 * Represents an autonomous routine; a chain of actions to run in sequence.
 * @author Evan Geng
 */
public interface IAutoRoutine {

    /**
     * Queues an action to be done until a condition is no longer satisfied.
     * @param condition The condition to check.
     * @param action The action to execute. Be sure to feed the watchdogs!
     * @return This autonomous routine instance, for chaining.
     */
    IAutoRoutine doWhile(ICondition condition, Runnable action);

    /**
     * Queues an action to be done until a condition is satisfied.
     * @param condition The condition to check.
     * @param action The action to execute. Be sure to feed the watchdogs!
     * @return This autonomous routine instance, for chaining.
     */
    IAutoRoutine doUntil(ICondition condition, Runnable action);

    /**
     * Queues an action to be done for a specific amount of time.
     * @param ms The number of milliseconds to run for.
     * @param action The action to execute. Be sure to feed the watchdogs!
     * @return This autonomous routine instance, for chaining.
     */
    IAutoRoutine doFor(long ms, Runnable action);

    /**
     * Queues an action to be done for a specific amount of time.
     * @param time The time to run for.
     * @param unit The unit of the given time.
     * @param action The action to execute. Be sure to feed the watchdogs!
     * @return This autonomous routine instance, for chaining.
     */
    IAutoRoutine doFor(long time, TimeUnit unit, Runnable action);

    /**
     * Sets this routine as the active routine.
     * @return This autonomous routine instance, for chaining.
     */
    IAutoRoutine setEnabled();
    
}
