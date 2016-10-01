package org.iowacityrobotics.roboed.frcimpl;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.api.actuator.IActuatorRegistry;
import org.iowacityrobotics.roboed.api.auto.IAutoManager;
import org.iowacityrobotics.roboed.api.event.IEventBus;
import org.iowacityrobotics.roboed.api.sensor.ISensorRegistry;
import org.iowacityrobotics.roboed.frcimpl.actuator.FRCActuatorRegistry;
import org.iowacityrobotics.roboed.frcimpl.auto.FRCAutoManager;
import org.iowacityrobotics.roboed.frcimpl.event.FRCEventBus;
import org.iowacityrobotics.roboed.frcimpl.sensor.FRCSensorRegistry;

public class FRCRobot extends IterativeRobot implements IRobot {
    
    private FRCEventBus eventBus;
    private FRCSensorRegistry sensorRegistry;
    private FRCActuatorRegistry actuatorRegistry;
    private FRCAutoManager autoManager;
    
    public FRCRobot() {
        this.eventBus = new FRCEventBus();
        this.sensorRegistry = new FRCSensorRegistry();
        this.actuatorRegistry = new FRCActuatorRegistry();
    }

    @Override
    public IEventBus eventBus() {
        return eventBus;
    }

    @Override
    public ISensorRegistry sensors() {
        return sensorRegistry;
    }

    @Override
    public IActuatorRegistry actuators() {
        return actuatorRegistry;
    }

    @Override
    public IAutoManager autonomous() {
        return autoManager;
    }

}
