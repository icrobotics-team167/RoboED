package org.iowacityrobotics.roboed.util.primitive;

@FunctionalInterface
public interface IIntTBiConsumer<T> {

	void accept(int a, T b);
	
}
