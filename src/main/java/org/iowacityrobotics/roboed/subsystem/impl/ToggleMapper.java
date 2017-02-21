package org.iowacityrobotics.roboed.subsystem.impl;

import org.iowacityrobotics.roboed.data.mapper.Mapper;

/**
 * Mapper that causes an input boolean control to become toggleable.
 * @author Evan Geng
 */
public class ToggleMapper extends Mapper<Boolean, Boolean> {

    /**
     * The state of the controller on the previous tick.
     */
    private boolean previousState = false;

    /**
     * The stored state of the controller.
     */
    private boolean storedState = false;

    @Override
    public Boolean apply(Boolean data) {
        if (data) {
            if (!previousState) {
                storedState = !storedState;
                previousState = true;
            }
        } else {
            previousState = false;
        }
        return storedState;
    }

}
