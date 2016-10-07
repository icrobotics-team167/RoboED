package org.iowacityrobotics.roboed.frcimpl.actuator.impl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.frcimpl.actuator.FRCActuator;
import org.iowacityrobotics.roboed.util.math.Maths;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCCANMotor extends FRCActuator<Double> {

    private final CANTalon backing;

    public FRCCANMotor(int port, DriverStation ds) {
        super(port);
        backing = new CANTalon(port);
    }

    @Override
    protected void set(Double value) {
        backing.set(Maths.clamp(value, -1D, 1D));
    }

}
