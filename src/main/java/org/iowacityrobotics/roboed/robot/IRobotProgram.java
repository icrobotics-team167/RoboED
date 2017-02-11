package org.iowacityrobotics.roboed.robot;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Implemented by the robot programmer. Run upon initialization of the robot.
 * @author Evan Geng
 */
public interface IRobotProgram {

    ServiceLoader<IRobotProgram> provider = ServiceLoader.load(IRobotProgram.class);

    static IRobotProgram getImplementation() {
        Iterator<IRobotProgram> implementations = provider.iterator();
        return implementations.hasNext() ? implementations.next() : null;
    }

    /**
     * Initialize the robot.
     * @param robot The robot to be initialized.
     */
    void init(Robot robot);

}
