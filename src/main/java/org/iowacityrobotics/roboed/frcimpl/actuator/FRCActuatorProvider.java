package org.iowacityrobotics.roboed.frcimpl.actuator;

import edu.wpi.first.wpilibj.DriverStation;

public class FRCActuatorProvider {

    private final DriverStation ds;

    public FRCActuatorProvider(DriverStation ds) {
        this.ds = ds;
    }

    public <T> FRCActuator<T> get(int id, String type) {
        return null;
    }

}
