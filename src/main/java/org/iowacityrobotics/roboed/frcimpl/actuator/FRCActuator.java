package org.iowacityrobotics.roboed.frcimpl.actuator;

import org.iowacityrobotics.roboed.api.actuator.IActuator;
import org.iowacityrobotics.roboed.util.function.Lambdas;

import java.util.function.BiConsumer;

public abstract class FRCActuator<T> implements IActuator<T> {

    private final int id;
    private BiConsumer<T, T> onMutation = Lambdas.noopBinary();

    public FRCActuator(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
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
