package org.iowacityrobotics.roboed.frcimpl.actuator;

import org.iowacityrobotics.roboed.api.actuator.IActuator;
import org.iowacityrobotics.roboed.util.function.Lambdas;

import java.util.function.BiConsumer;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public abstract class FRCActuator<T> implements IActuator<T> {

    private final int port;
    private BiConsumer<T, T> onMutation = Lambdas.noopBinary();

    public FRCActuator(int port) {
        this.port = port;
    }

    @Override
    public int port() {
        return port;
    }

    @Override
    public void onMutation(BiConsumer<T, T> handler) {
        onMutation = onMutation.andThen(handler);
    }

    @Override
    public void accept(T t) {
        onMutation.accept(null, t);
        set(t);
    }

    protected abstract void set(T t);

}
