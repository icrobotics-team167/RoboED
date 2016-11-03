package org.iowacityrobotics.roboed;

import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.impl.FRCRobot;

/**
 * Retrieves instances of {@link IRobot} using the appropriate implementation of RoboED.
 * @author Evan Geng
 */
public class RobotAPI {

    /**
     * The robot instance.
     */
    private static final IRobot INSTANCE = new FRCRobot();
    
    /**
     * Gets the appropriate instance of {@link IRobot}.
     * @return The requested {@link IRobot}.
     */
    public static IRobot getRobot() {
        return INSTANCE;
    }
    
}
