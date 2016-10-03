package org.iowacityrobotics.roboed.api.event.mode;

import org.iowacityrobotics.roboed.api.auto.IAutoManager;
import org.iowacityrobotics.roboed.api.event.AbstractEvent;

/**
 * Event fired when the robot enters autonomous mode.
 * @author Evan Geng
 */
public class AutoInitEvent extends AbstractEvent {

    /**
     * The {@link IAutoManager} instance handling the routine to be run.
     */
    private final IAutoManager autoMan;
    
    /**
     * Creates a new event instance with the given autonomous manager.
     * @param autoMan The {@link IAutoManager} instance.
     */
    public AutoInitEvent(IAutoManager autoMan) {
        this.autoMan = autoMan;
    }
    
    /**
     * Gets the {@link IAutoManager} instance handling this event.
     * @return The autonomous manager.
     */
    public IAutoManager autoManager() {
        return autoMan;
    }
    
}
