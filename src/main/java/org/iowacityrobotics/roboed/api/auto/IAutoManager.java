package org.iowacityrobotics.roboed.api.auto;

public interface IAutoManager {

    IAutoRoutine createRoutine(String id);
    
    void setRoutine(String id);
    
}
