package org.iowacityrobotics.roboed.api.event;

import java.util.function.Consumer;

/**
 * Represents an event bus, where events are posted to be accepted by handlers.
 * @author Evan Geng
 */
public interface IEventBus {

    /**
     * Registers an event handler.
     * @param event The event's class.
     * @param handler The handler.
     * @param <T> The type of event to accept.
     */
    <T extends IEvent> void on(Class<T> event, Consumer<T> handler);

    /**
     * Posts an event to be caught by handlers.
     * @param event The event to post.
     * @param <T> The type of the event to post.
     */
    <T extends IEvent> void post(T event);
    
}
