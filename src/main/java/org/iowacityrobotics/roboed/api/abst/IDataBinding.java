package org.iowacityrobotics.roboed.api.abst;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface IDataBinding<T> {

	<O> IDataBinding<O> map(Function<T, O> mapper);

	<I, O> IDataBinding<O> map(IDataBinding<I> source, BiFunction<T, I, O> mapper);

	void bind(IObservableSetter<T> setter);

}
