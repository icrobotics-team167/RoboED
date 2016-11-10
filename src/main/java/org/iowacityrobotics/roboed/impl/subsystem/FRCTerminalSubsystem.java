package org.iowacityrobotics.roboed.impl.subsystem;

import org.iowacityrobotics.roboed.api.data.DataUnavailableException;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;

/**
 * @author Evan Geng
 */
public abstract class FRCTerminalSubsystem<T> extends FRCSubsystem<T, Void> {
    
    private IDataSource<T> input;

    protected <P extends ISubsystemProvider<T, Void>> FRCTerminalSubsystem(ISubsystemType<T, Void, P> type, int id) {
        super(type, id);
    }
    
    @Override
    public void bind(IDataSource<T> input) {
        this.input = input;
    }

    @Override
    public IDataSource<Void> output() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void reset() {
        this.input = null;
    }
    
    public void tick() {
        try {
            processData(input.request());
        } catch (DataUnavailableException ignored) { }
    }
    
    protected abstract void processData(T data);
    
}
