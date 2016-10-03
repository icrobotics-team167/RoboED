package org.iowacityrobotics.roboed.api.abst;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * A supplier function that calls handlers when its value changes.
 * @param <T> The type of values supplied.
 * @author Evan Geng
 */
public interface IObservableGetter<T> extends Supplier<T> {

    /**
     * Registers a handler for value mutation.
     * @param handler The handler to be registered; the first parameter is the previous value and the second is the newly assigned value.
     */
    void onMutation(BiConsumer<T, T> handler);

    /**
     * Creates a data conduit from this supplier which can be bound to an {@link IObservableSetter}.
     * @return The newly created data conduit.
     */
    IDataBinding<T> binding();

}
