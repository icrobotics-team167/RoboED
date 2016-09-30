package org.iowacityrobotics.roboed.api.abst;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface IObservableGetter<T> extends Supplier<T> {

	void onMutation(BiConsumer<T, T> handler);

	IDataBinding binding();

}
