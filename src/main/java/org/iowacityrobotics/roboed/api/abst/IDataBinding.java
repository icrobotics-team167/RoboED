package org.iowacityrobotics.roboed.api.abst;

import java.util.function.Function;

/**
 * Represents a data conduit between an {@link IObservableGetter} and an {@link IObservableSetter}.
 * @param <T> The type of the data being transmitted through this conduit.
 * @author Evan Geng
 */
public interface IDataBinding<T> {

    /**
     * Maps data flowing through this pipeline via a function.
     * @param mapper The function to process the data with.
     * @param <O> The type of the function's output.
     * @return An {@link IDataBinding} with the additional pipe segment.
     */
    <O> IDataBinding<O> map(Function<T, O> mapper);

    /**
     * Terminates this pipeline at the given data output.
     * @param setter The desired output for the data.
     */
    void bind(IObservableSetter<T> setter);

}
