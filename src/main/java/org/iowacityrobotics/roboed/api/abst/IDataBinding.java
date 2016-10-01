package org.iowacityrobotics.roboed.api.abst;

import java.util.function.Function;

public interface IDataBinding<T> {

    <O> IDataBinding<O> map(Function<T, O> mapper);

    void bind(IObservableSetter<T> setter);

}
