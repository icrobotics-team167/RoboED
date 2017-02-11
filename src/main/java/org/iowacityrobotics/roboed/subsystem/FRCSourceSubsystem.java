package org.iowacityrobotics.roboed.subsystem;

import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;

/**
 * @author Evan Geng
 */
public abstract class FRCSourceSubsystem<T> extends FRCSubsystem<Void, T> {
    
    protected <P extends ISubsystemProvider<Void, T>> FRCSourceSubsystem(ISubsystemType<Void, T, P> type) {
        super(type);
    }

    @Override
    public void bind(IDataSource<Void> input) {
        throw new UnsupportedOperationException();
    }

}
