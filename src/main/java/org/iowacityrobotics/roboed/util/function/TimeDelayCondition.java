package org.iowacityrobotics.roboed.util.function;

import java.util.concurrent.TimeUnit;

/**
 * An {@link ICondition} that's met once a certain amount of time has passed since the first call to {@link #isMet()}.
 * @author Evan Geng
 */
public class TimeDelayCondition implements ICondition {

    /**
     * The duration to wait before considering the condition met.
     */
    private long ms;

    /**
     * The time of the first call to {@link #isMet()}.
     */
    private long startTime;

    /**
     * Whether {@link #isMet()} has been called yet.
     */
    private boolean started;

    /**
     * Creates a new TimeDelayCondition with the given duration.
     * @param ms Number of milliseconds.
     */
    public TimeDelayCondition(long ms) {
        this.ms = ms;
    }

    /**
     * Creates a new TimeDelayCondition with the given duration.
     * @param time Time to wait before the condition is met.
     * @param unit The unit of time.
     */
    public TimeDelayCondition(long time, TimeUnit unit) {
        this(unit.toMillis(time));
    }

    @Override
    public boolean isMet() {
        if (!started) {
            started = true;
            startTime = System.currentTimeMillis();
            return false;
        }
        else
            return System.currentTimeMillis() - startTime > ms;
    }

}
