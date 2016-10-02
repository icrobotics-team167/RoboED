package org.iowacityrobotics.roboed.api.abst;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A consumer function that calls handlers when the received value modifies the underlying property.
 * @param <T> The type of values to accept.
 * @author Evan Geng
 */
public interface IObservableSetter<T> extends Consumer<T> {

    /**
     * Registers a handler for value mutation.
     * @param handler The handler to be registered; the first parameter is the previous value and the second is the newly assigned value.
     */
    void onMutation(BiConsumer<T, T> handler);

}
