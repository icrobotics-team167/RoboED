package org.iowacityrobotics.roboed.event;

import java.util.function.Consumer;

import org.iowacityrobotics.roboed.util.function.Lambdas;

/**
 * An event bus that broadcasts events with one parameter.
 * @author Evan Geng
 */
public class UnaryEventStream<T> implements Consumer<T> {

    /**
     * The handler stack.
     */
    private Consumer<T> handler = Lambdas.noopUnary();

    /**
     * Registers an event handler, thereby adding it to the stack.
     * @param handler The new event handler.
     * @return This event stream, for chaining.
     */
    public UnaryEventStream<T> addHandler(Consumer<T> handler) {
        this.handler = this.handler.andThen(handler);
        return this;
    }

    /**
     * Broadcasts an event to all handlers on the stack.
     * @param event The event's parameter.
     */
    @Override
    public void accept(T event) {
        handler.accept(event);
    }
    
}
