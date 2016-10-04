package org.iowacityrobotics.roboed.api.event.button;

import org.iowacityrobotics.roboed.api.event.AbstractEvent;

/**
 * Event fired when a button is pressed down.
 * @author Evan Geng
 */
public class ButtonPressEvent extends AbstractEvent {

    /**
     * The port the controller is plugged into.
     */
    private final int controllerPort;
    
    /**
     * The ID of the pressed button.
     */
    private final int buttonId;
    
    /**
     * Creates an event instance with the given controller port and button ID.
     * @param controllerPort The related controller's port.
     * @param buttonId The ID of the pressed button.
     */
    public ButtonPressEvent(int controllerPort, int buttonId) {
        this.controllerPort = controllerPort;
        this.buttonId = buttonId;
    }
    
    /**
     * Returns the port the controller is plugged into.
     * @return The related controller's port.
     */
    public int controller() {
        return controllerPort;
    }
    
    /**
     * Returns the ID of the pressed button.
     * @return The pressed button's ID.
     */
    public int button() {
        return buttonId;
    }
    
}
