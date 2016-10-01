package org.iowacityrobotics.roboed.frcimpl.actuator;

import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.frcimpl.sensor.FRCSensor;

public class FRCSensorProvider {

    private final DriverStation ds;

    public FRCSensorProvider(DriverStation ds) {
        this.ds = ds;
    }

    public <T> FRCSensor<T> get(int id, String type) {
        return null;
    }

}
