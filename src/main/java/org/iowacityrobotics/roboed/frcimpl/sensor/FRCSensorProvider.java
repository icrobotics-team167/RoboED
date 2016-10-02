package org.iowacityrobotics.roboed.frcimpl.sensor;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCSensorProvider {

    private final DriverStation ds;

    public FRCSensorProvider(DriverStation ds) {
        this.ds = ds;
    }

    public <T> FRCSensor<T> get(int id, String type) {
        return null;
    } // TODO Finish implementation

}
