package org.iowacityrobotics.roboed.api.auto;

import java.util.function.Predicate;

import org.iowacityrobotics.roboed.api.sensor.ISensorRegistry;

public interface IAutoRoutine {

	IAutoRoutine doWhile(Predicate<ISensorRegistry> condition, Runnable action);
	
	IAutoRoutine doUntil(Predicate<ISensorRegistry> condition, Runnable action);
	
	IAutoRoutine doFor(long ms, Runnable action);
	
}
