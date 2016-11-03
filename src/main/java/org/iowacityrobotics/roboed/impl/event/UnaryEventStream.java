package org.iowacityrobotics.roboed.impl.event;

import java.util.function.Consumer;

import org.iowacityrobotics.roboed.util.function.Lambdas;

/** 
 * @author Evan Geng
 */
public class UnaryEventStream<T> implements Consumer<T> {

    private Consumer<T> handler = Lambdas.noopUnary();
    
    public UnaryEventStream<T> addHandler(Consumer<T> handler) {
        this.handler = this.handler.andThen(handler);
        return this;
    }
    
    @Override
    public void accept(T event) {
        handler.accept(event);
    }
    
}
