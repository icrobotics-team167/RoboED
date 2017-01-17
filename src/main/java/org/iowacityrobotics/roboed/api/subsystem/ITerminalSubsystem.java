package org.iowacityrobotics.roboed.api.subsystem;

import org.iowacityrobotics.roboed.api.data.IDataSource;

/**
 * Represents a terminal subsystem.
 * @param <I> The data type accepted by this subsystem.
 * @author Evan Geng
 */
public interface ITerminalSubsystem<I> extends ISubsystem<I, Void> {

    @Override
    default IDataSource<Void> output() {
        throw new UnsupportedOperationException();
    }

    /**
     * Instructs the subsystem to request and process one frame of data.
     */
    void tick();

}
