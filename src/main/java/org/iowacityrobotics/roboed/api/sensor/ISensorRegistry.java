package org.iowacityrobotics.roboed.api.sensor;

public interface ISensorRegistry {

	void put(ISensor<?> sensor);
	
	<T> ISensor<T> get(int id);
	
}
