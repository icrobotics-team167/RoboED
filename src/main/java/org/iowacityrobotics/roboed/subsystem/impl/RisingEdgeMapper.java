package org.iowacityrobotics.roboed.subsystem.impl;

import org.iowacityrobotics.roboed.data.mapper.Mapper;

/**
 * A rising-edge gate. Only outputs true on the frame when input rises from false to true.
 * @author Evan Geng
 */
public class RisingEdgeMapper extends Mapper<Boolean, Boolean> {

    /**
     * The input state on the previous frame.
     */
    private boolean lastFrame;

    @Override
    public Boolean apply(Boolean data) {
        boolean out = false;
        if (!lastFrame && data) out = true;
        lastFrame = data;
        return out;
    }

}
