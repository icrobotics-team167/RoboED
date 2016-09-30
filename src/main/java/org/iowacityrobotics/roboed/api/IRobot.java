package org.iowacityrobotics.roboed.api;

import org.iowacityrobotics.roboed.api.actuator.IActuatorRegistry;
import org.iowacityrobotics.roboed.api.auto.IAutoManager;
import org.iowacityrobotics.roboed.api.event.IEventBus;
import org.iowacityrobotics.roboed.api.sensor.ISensorRegistry;

public interface IRobot {

    IEventBus eventBus();
    
    ISensorRegistry sensors();
    
    IActuatorRegistry actuators();
    
    IAutoManager autonomous();
    
}
