package org.iowacityrobotics.roboed.api.event.mode;

import org.iowacityrobotics.roboed.api.auto.IAutoRoutine;
import org.iowacityrobotics.roboed.api.event.AbstractEvent;

/**
 * Event fired every autonomous tick.
 * @author Evan Geng
 */
public class AutoTickEvent extends AbstractEvent {

    /**
     * The autonomous routine being run. Cannot be mutated while the robot is in autonomous mode.
     */
    private final IAutoRoutine routine;
    
    /**
     * Creates a new event instances with the given {@link IAutoRoutine}.
     * @param routine The routine being run by the robot.
     */
    public AutoTickEvent(IAutoRoutine routine) {
        this.routine = routine;
    }
    
    /**
     * Gets the current autonomous routine.
     * @return The {@link IAutoRoutine} instance.
     */
    public IAutoRoutine autoRoutine() {
        return routine;
    }
    
}
