package org.iowacityrobotics.roboed.api.auto;

import org.iowacityrobotics.roboed.util.function.ICondition;

import java.util.concurrent.TimeUnit;

public interface IAutoRoutine {

    IAutoRoutine doWhile(ICondition condition, Runnable action);
    
    IAutoRoutine doUntil(ICondition condition, Runnable action);
    
    IAutoRoutine doFor(long ms, Runnable action);
    
    IAutoRoutine doFor(long time, TimeUnit unit, Runnable action);

    IAutoRoutine setEnabled();
    
}
