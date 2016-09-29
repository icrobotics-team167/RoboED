package org.iowacityrobotics.roboed;

import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.impl2016.Robot2016;

public class RobotAPI {
	
	public static IRobot getRobot() {
		return new Robot2016(); // TODO Modularize
	}
	
}
