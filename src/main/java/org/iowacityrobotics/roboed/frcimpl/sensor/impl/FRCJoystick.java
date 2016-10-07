package org.iowacityrobotics.roboed.frcimpl.sensor.impl;

import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.frcimpl.sensor.FRCSensor;
import org.iowacityrobotics.roboed.util.math.Vector2;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCJoystick extends FRCSensor<Vector2> {

    private final int controllerId, axisId1, axisId2;
    private final DriverStation ds;

    public FRCJoystick(int port, DriverStation ds) {
        super(port);
        this.controllerId = port & 0b000000001111;
        this.axisId1 = (port & 0b000011110000) >> 4;
        this.axisId2 = (port & 0b111100000000) >> 8;
        this.ds = ds;
    }

    @Override
    protected Vector2 doGet() {
        return new Vector2(ds.getStickAxis(controllerId, axisId1), ds.getStickAxis(controllerId, axisId2));
    }

}
