package org.iowacityrobotics.roboed.robot;

import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.sink.Sink;
import org.iowacityrobotics.roboed.util.function.ICondition;
import org.iowacityrobotics.roboed.util.function.IConditionFactory;

import java.util.concurrent.TimeUnit;

/**
 * Utilities to help with controlling program flow.
 * @author Evan Geng
 */
public final class Flow {

    /**
     * The operations thread.
     */
    private static Thread opThread = new Thread();

    /**
     * Run an operation on the operations thread.
     * @param func The operation to run.
     */
    static void run(Runnable func) {
        if (opThread != null) {
            opThread.interrupt();
            while (opThread.isAlive()) {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        opThread = new Thread(() -> {
            Data.reset(true);
            func.run();
            Data.reset(true);
            while (!Thread.currentThread().isInterrupted())
                Sink.tickAll();
        });
        opThread.start();
    }

    /**
     * Waits until a condition is satisfied.
     * @param factory The provider for the condition.
     */
    public static void waitUntil(IConditionFactory factory) {
        waitUntil(factory.create());
    }

    /**
     * Waits until a condition is satisfied.
     * @param condition The condition to wait for.
     */
    public static void waitUntil(ICondition condition) {
        if (Thread.currentThread() != opThread)
            throw new IllegalStateException("Wait can only be called on operations thread!");
        while (!condition.isMet() && !Thread.currentThread().isInterrupted())
            Sink.tickAll();
    }

    /**
     * Waits while a condition is satisfied.
     * @param factory The provider for the condition.
     */
    public static void waitWhile(IConditionFactory factory) {
        waitUntil(factory.negate());
    }

    /**
     * Waits while a condition is satisfied.
     * @param condition The provider for the condition.
     */
    public static void waitWhile(ICondition condition) {
        waitUntil(condition.negate());
    }

    /**
     * Wait for the given amount of time.
     * @param time Time to wait.
     * @param unit Units of time.
     */
    public static void waitFor(long time, TimeUnit unit) {
        waitFor(unit.toMillis(time));
    }

    /**
     * Wait for the given amount of time.
     * @param ms Milliseconds to wait.
     */
    public static void waitFor(long ms) {
        final long initTime = System.currentTimeMillis();
        waitUntil(() -> System.currentTimeMillis() - initTime > ms);
    }

    /**
     * Wait until the next robot mode change.
     */
    public static void waitInfinite() {
        waitUntil(() -> false);
    }

}
