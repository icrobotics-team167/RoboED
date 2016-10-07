package org.iowacityrobotics.roboed.frcimpl.sensor.impl;

import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.frcimpl.sensor.FRCSensor;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCButton extends FRCSensor<Boolean> {

    private final int controllerId, buttonId;
    private final DriverStation ds;

    public FRCButton(int port, DriverStation ds) {
        super(port);
        this.controllerId = port & 0b00001111;
        this.buttonId = (port & 0b11110000) << 4;
        this.ds = ds;
    }

    @Override
    protected Boolean doGet() {
        return ds.getStickButton(controllerId, (byte)buttonId);
    }

}
