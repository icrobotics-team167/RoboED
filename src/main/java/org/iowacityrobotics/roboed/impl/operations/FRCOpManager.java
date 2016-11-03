package org.iowacityrobotics.roboed.impl.operations;

import org.iowacityrobotics.roboed.api.RobotMode;
import org.iowacityrobotics.roboed.api.operations.IOpMode;
import org.iowacityrobotics.roboed.api.operations.IOperationsManager;

/** 
 * @author Evan Geng
 */
public class FRCOpManager implements IOperationsManager {

    @Override
    public IOpMode getOpMode(String id) {
        return null; // TODO Implement
    }

    @Override
    public void setDefaultOpMode(RobotMode status, String id) {
        // TODO Implement
    }

    @Override
    public void setOpMode(String id) {
        // TODO Implement
    }
    
    public void modeChanged(RobotMode newMode) {
        // TODO Implement
    }
    
    public void tick() {
        // TODO Implement
    }

}
