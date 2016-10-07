package org.iowacityrobotics.roboed.frcimpl.sensor.impl;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.frcimpl.sensor.FRCSensor;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCAnalogSensor extends FRCSensor<Double> {

    private final AnalogInput backing;

    public FRCAnalogSensor(int port, DriverStation ds) {
        super(port);
        this.backing = new AnalogInput(port);
    }

    @Override
    protected Double doGet() {
        return backing.getVoltage();
    }

}
