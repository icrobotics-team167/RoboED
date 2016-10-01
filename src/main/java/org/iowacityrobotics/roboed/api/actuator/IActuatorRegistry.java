package org.iowacityrobotics.roboed.api.actuator;

public interface IActuatorRegistry {

    <T> IActuator<T> put(int id, String type);
    
    <T> IActuator<T> get(int id);
    
}
