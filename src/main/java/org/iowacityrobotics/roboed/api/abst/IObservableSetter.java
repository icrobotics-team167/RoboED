package org.iowacityrobotics.roboed.api.abst;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IObservableSetter<T> extends Consumer<T> {

	void onMutation(BiConsumer<T, T> handler);

}
