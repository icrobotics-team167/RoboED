package org.iowacityrobotics.roboed;

import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.frcimpl.FRCRobot;

/**
 * Retrieves instances of {@link IRobot} using the appropriate implementation of RoboED.
 * @author Evan Geng
 */
public class RobotAPI {

    /**
     * Gets the appropriate instance of {@link IRobot}.
     * @return The requested {@link IRobot}.
     */
    public static IRobot getRobot() {
        return new FRCRobot(); // TODO Modularize
    }
    
}
