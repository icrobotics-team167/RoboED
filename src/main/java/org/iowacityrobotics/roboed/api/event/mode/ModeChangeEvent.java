package org.iowacityrobotics.roboed.api.event.mode;

import org.iowacityrobotics.roboed.api.RobotMode;
import org.iowacityrobotics.roboed.api.event.AbstractEvent;

/**
 * Event fired when the robot's operational mode changes.
 * @author Evan Geng
 */
public class ModeChangeEvent extends AbstractEvent {

    /**
     * The operational mode before the change.
     */
    private final RobotMode oldMode;
    
    /**
     * The operational mode after the change.
     */
    private final RobotMode newMode;
   
    /**
     * Creates a new event instance.
     * @param oldMode The previous operational mode.
     * @param newMode The new operational mode.
     */
    public ModeChangeEvent(RobotMode oldMode, RobotMode newMode) {
        this.oldMode = oldMode;
        this.newMode = newMode;
    }
    
    /**
     * Gets the operational mode before the mode change.
     * @return Old operational mode.
     */
    public RobotMode oldMode() {
        return oldMode;
    }
    
    /**
     * Gets the operational mode after the mode change.
     * @return New operational mode.
     */
    public RobotMode newMode() {
        return newMode;
    }
    
}
