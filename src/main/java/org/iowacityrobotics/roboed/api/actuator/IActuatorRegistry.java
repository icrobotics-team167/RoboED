package org.iowacityrobotics.roboed.api.actuator;

public interface IActuatorRegistry {

	void put(IActuator<?> sensor);
	
	<T> IActuator<T> get(int id);
	
}
