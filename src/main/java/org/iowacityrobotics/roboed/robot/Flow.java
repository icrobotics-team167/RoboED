package org.iowacityrobotics.roboed.robot;

import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.sink.Sink;
import org.iowacityrobotics.roboed.util.collection.StackNode;
import org.iowacityrobotics.roboed.util.function.ICondition;
import org.iowacityrobotics.roboed.util.function.IConditionFactory;
import org.iowacityrobotics.roboed.util.logging.Logs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Utilities to help with controlling program flow.
 * @author Evan Geng
 */
public final class Flow {

    /**
     * The executor service handling the operations thread.
     */
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * The future representing the current operations thread.
     */
    private static Future<?> future;

    /**
     * The context of the current wait call.
     */
    private static StackNode<WaitingContext> waitCtx;

    /**
     * A flag telling the topmost wait call to break.
     */
    private static final AtomicBoolean shouldBreak = new AtomicBoolean(false);

    /**
     * Run an operation on the operations thread.
     * @param func The operation to run.
     */
    static void run(Runnable func) {
        if (future != null) future.cancel(true);
        future = executor.submit(() -> {
            Data.reset(false);
            waitCtx = new StackNode<>(new WaitingContext());
            try {
                func.run();
            } catch (EndOp e) {
                Logs.info("Opmode ended early");
                return;
            } catch (Exception e) {
                Logs.error("Errored while running opmode!", e);
            }
            Data.reset(false);
            while (!Thread.currentThread().isInterrupted())
                Sink.tickAll();
        });
    }

    /**
     * Registers a function to run continuously in a loop on the next wait.
     * @param func The function.
     */
    public static void whileWaiting(Runnable func) {
        waitCtx.getValue().waitingFunc = func;
    }

    /**
     * Breaks the topmost wait.
     */
    public static void breakWait() {
        shouldBreak.set(true);
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
        if (!Thread.currentThread().isInterrupted()) {
            Logs.debug("OpThread > Func > Wait : Start");
            WaitingContext ctx = waitCtx.getValue();
            waitCtx = waitCtx.extend(new WaitingContext());
            while (!condition.isMet() && !Thread.currentThread().isInterrupted() && !shouldBreak.get()) {
                Sink.tickAll();
                if (ctx.waitingFunc != null)
                    ctx.waitingFunc.run();
            }
            Logs.debug("OpThread > Func > Wait : End");
            shouldBreak.set(false);
            waitCtx = waitCtx.getParent();
        }
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

    /**
     * Ends the currently running opmode.
     */
    public static void end() {
        throw new EndOp();
    }

    /**
     * Utility class that stores state concerning the current wait call.
     */
    private static class WaitingContext {

        /**
         * The function to run while waiting.
         */
        Runnable waitingFunc = null;

    }

    /**
     * Exception to be thrown and caught when an opmode needs to end prematurely.
     */
    private static class EndOp extends RuntimeException {
        // NO-OP
    }

}
