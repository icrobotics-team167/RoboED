package org.iowacityrobotics.roboed.api.actuator;

public interface IActuatorRegistry {

    void put(IActuator<?> actuator);
    
    <T> IActuator<T> get(int id);
    
}
