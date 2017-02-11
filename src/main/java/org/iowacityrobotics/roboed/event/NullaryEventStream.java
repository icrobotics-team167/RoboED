package org.iowacityrobotics.roboed.event;

import org.iowacityrobotics.roboed.util.function.Lambdas;

/**
 * An event bus that broadcasts events with no parameters.
 * @author Evan Geng
 */
public class NullaryEventStream implements Runnable {

    /**
     * The handler stack.
     */
    private Runnable handler = Lambdas.noopNullary();

    /**
     * Registers an event handler, thereby adding it to the stack.
     * @param handler The new event handler.
     * @return This event stream, for chaining.
     */
    public NullaryEventStream addHandler(Runnable handler) {
        this.handler = Lambdas.compose(this.handler, handler);
        return this;
    }

    /**
     * Broadcasts an event to all handlers on the stack.
     */
    @Override
    public void run() {
        handler.run();
    }
    
}
