package org.iowacityrobotics.roboed.frcimpl.actuator.impl;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TalonSRX;
import org.iowacityrobotics.roboed.frcimpl.actuator.FRCActuator;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCPWMMotor extends FRCActuator<Double> {

    private final TalonSRX backing;

    public FRCPWMMotor(int port, DriverStation ds) {
        super(port);
        this.backing = new TalonSRX(port);
    }

    @Override
    protected void set(Double value) {
        backing.set(value);
    }

}
