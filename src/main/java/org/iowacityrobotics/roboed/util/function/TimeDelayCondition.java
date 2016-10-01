package org.iowacityrobotics.roboed.util.function;

import java.util.concurrent.TimeUnit;

public class TimeDelayCondition implements ICondition {

    private long ms, startTime;
    private boolean started;

    public TimeDelayCondition(long ms) {
        this.ms = ms;
    }

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
