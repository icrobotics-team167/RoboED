package org.iowacityrobotics.roboed.api.sensor;

public interface ISensorRegistry {

    <T> ISensor<T> put(int id, String type);
    
    <T> ISensor<T> get(int id);
    
}
