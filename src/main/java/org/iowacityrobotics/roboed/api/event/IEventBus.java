package org.iowacityrobotics.roboed.api.event;

import java.util.function.Consumer;

public interface IEventBus {

    <T extends IEvent> void on(Class<T> event, Consumer<T> handler);

    <T extends IEvent> void post(T event);
    
}
