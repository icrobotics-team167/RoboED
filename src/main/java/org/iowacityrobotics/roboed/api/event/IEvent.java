package org.iowacityrobotics.roboed.api.event;

/**
 * Represents an event to be posted to an {@link IEventBus}.
 * @author Evan Geng
 */
public interface IEvent {

    /**
     * Gets the time at which this event was fired.
     * @return The event's timestamp.
     */
    long timestamp();
    
}
