package org.iowacityrobotics.roboed.frcimpl.auto;

import org.iowacityrobotics.roboed.api.auto.IAutoRoutine;
import org.iowacityrobotics.roboed.util.Pair;
import org.iowacityrobotics.roboed.util.function.ICondition;
import org.iowacityrobotics.roboed.util.function.TimeDelayCondition;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCAutoRoutine implements IAutoRoutine {

    FRCAutoManager autoMan;
    String id;
    List<Pair<ICondition, Runnable>> actions;

    FRCAutoRoutine(FRCAutoManager autoMan, String id) {
        this.autoMan = autoMan;
        this.id = id;
    }

    @Override
    public IAutoRoutine doWhile(ICondition condition, Runnable action) {
        actions.add(Pair.of(condition, action));
        return this;
    }

    @Override
    public IAutoRoutine doUntil(ICondition condition, Runnable action) {
        return doWhile(condition.negate(), action);
    }

    @Override
    public IAutoRoutine doFor(long ms, Runnable action) {
        return doUntil(new TimeDelayCondition(ms), action);
    }

    @Override
    public IAutoRoutine doFor(long time, TimeUnit unit, Runnable action) {
        return doUntil(new TimeDelayCondition(time, unit), action);
    }

    @Override
    public IAutoRoutine setEnabled() {
        autoMan.setRoutine(id);
        return this;
    }

}
