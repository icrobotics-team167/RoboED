package org.iowacityrobotics.roboed.impl.event;

import org.iowacityrobotics.roboed.util.function.Lambdas;

/** 
 * @author Evan Geng
 */
public class NullaryEventStream implements Runnable {

    private Runnable handler = Lambdas.noopNullary();
    
    public NullaryEventStream addHandler(Runnable handler) {
        this.handler = Lambdas.compose(this.handler, handler);
        return this;
    }
    
    @Override
    public void run() {
        handler.run();
    }
    
}
