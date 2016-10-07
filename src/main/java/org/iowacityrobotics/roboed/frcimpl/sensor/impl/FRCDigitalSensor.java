package org.iowacityrobotics.roboed.frcimpl.sensor.impl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.frcimpl.sensor.FRCSensor;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCDigitalSensor extends FRCSensor<Boolean> {

    private final DigitalInput backing;

    public FRCDigitalSensor(int port, DriverStation ds) {
        super(port);
        this.backing = new DigitalInput(port);
    }

    @Override
    protected Boolean doGet() {
        return backing.get();
    }

}
