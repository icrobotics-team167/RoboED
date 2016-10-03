package org.iowacityrobotics.roboed.api.event.mode;

import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.api.event.AbstractEvent;

/**
 * Event fired when the robot is initialized.
 * @author Evan Geng
 */
public class RobotInitEvent extends AbstractEvent {
    
    /**
     * The {@link IRobot} instance this event refers to.
     */
    private final IRobot robot;
    
    /**
     * Creates a new RobotInitEvent for the given {@link IRobot}.
     * @param robot The robot being initialized.
     */
    public RobotInitEvent(IRobot robot) {
        this.robot = robot;
    }
    
    /**
     * Gets the {@link IRobot} this event refers to.
     * @return The robot being initialized. 
     */
    public IRobot robot() {
        return robot;
    }
    
}
