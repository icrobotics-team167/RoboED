package org.iowacityrobotics.roboed.api.auto;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

public interface IAutoRoutine {

    IAutoRoutine doWhile(BooleanSupplier condition, Runnable action);
    
    IAutoRoutine doUntil(BooleanSupplier condition, Runnable action);
    
    IAutoRoutine doFor(long ms, Runnable action);
    
    IAutoRoutine doFor(long time, TimeUnit unit, Runnable action);
    
}
