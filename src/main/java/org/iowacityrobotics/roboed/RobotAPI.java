package org.iowacityrobotics.roboed;

import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.frcimpl.FRCRobot;

public class RobotAPI {
    
    public static IRobot getRobot() {
        return new FRCRobot(); // TODO Modularize
    }
    
}
