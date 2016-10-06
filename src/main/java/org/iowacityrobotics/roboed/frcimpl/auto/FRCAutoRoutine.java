package org.iowacityrobotics.roboed.frcimpl.auto;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.iowacityrobotics.roboed.api.auto.IAutoRoutine;
import org.iowacityrobotics.roboed.util.collection.Pair;
import org.iowacityrobotics.roboed.util.function.ICondition;
import org.iowacityrobotics.roboed.util.function.IConditionFactory;
import org.iowacityrobotics.roboed.util.function.TimeDelayCondition;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCAutoRoutine implements IAutoRoutine {

    FRCAutoManager autoMan;
    String id;
    List<Pair<IConditionFactory, Runnable>> actions;

    FRCAutoRoutine(FRCAutoManager autoMan, String id) {
        this.autoMan = autoMan;
        this.id = id;
    }

    @Override
    public IAutoRoutine doWhile(IConditionFactory conditionFactory, Runnable action) {
        actions.add(Pair.of(conditionFactory, action));
        return this;
    }
    
    @Override
    public IAutoRoutine doWhile(ICondition condition, Runnable action) {
        return doWhile(IConditionFactory.getter(condition), action);
    }

    @Override
    public IAutoRoutine doUntil(IConditionFactory conditionFactory, Runnable action) {
        return doWhile(conditionFactory.negate(), action);
    }
    
    @Override
    public IAutoRoutine doUntil(ICondition condition, Runnable action) {
        return doUntil(IConditionFactory.getter(condition), action);
    }

    @Override
    public IAutoRoutine doFor(long ms, Runnable action) {
        return doUntil(() -> new TimeDelayCondition(ms), action);
    }

    @Override
    public IAutoRoutine doFor(long time, TimeUnit unit, Runnable action) {
        return doUntil(() -> new TimeDelayCondition(time, unit), action);
    }

    @Override
    public IAutoRoutine setEnabled() {
        autoMan.setRoutine(id);
        return this;
    }

}
