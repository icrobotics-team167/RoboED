package org.iowacityrobotics.roboed.frcimpl.event;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;

import org.iowacityrobotics.roboed.api.event.IEvent;
import org.iowacityrobotics.roboed.api.event.IEventBus;
import org.iowacityrobotics.roboed.util.OneToManyMap;

public class FRCEventBus implements IEventBus {

    private final OneToManyMap<Class<? extends IEvent>, Consumer<? extends IEvent>, Collection<Consumer<? extends IEvent>>> handlers;
    
    public FRCEventBus() {
        handlers = new OneToManyMap<>(LinkedList::new);
    }
    
    @Override
    public <T extends IEvent> void on(Class<T> event, Consumer<T> handler) {
        handlers.put(event, handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IEvent> void post(T event) {
        handlers.stream().filter(p -> p.getA().isAssignableFrom(event.getClass())).map(e -> (Consumer<T>)e.getB()).forEach(h -> h.accept(event));
    }

}
