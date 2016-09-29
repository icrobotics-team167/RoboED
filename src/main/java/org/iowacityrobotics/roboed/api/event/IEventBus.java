package org.iowacityrobotics.roboed.api.event;

import java.util.function.Consumer;

public interface IEventBus {

	<T extends IEvent> void on(Consumer<T> handler);
	
}
