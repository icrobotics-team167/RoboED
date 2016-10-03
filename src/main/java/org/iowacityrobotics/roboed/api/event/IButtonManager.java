package org.iowacityrobotics.roboed.api.event;

/**
 * Manages button-based inputs and fires events related to them.
 * @author Evan Geng
 */
public interface IButtonManager {

    /**
     * Asks the button manager to listen to a controller.
     * @param controllerPort The port of the desired controller.
     * @param buttons The IDs of the buttons to listen to.
     */
    void subscribe(int controllerPort, int... buttons);
    
}
