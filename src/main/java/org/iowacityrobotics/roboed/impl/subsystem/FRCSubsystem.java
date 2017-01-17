package org.iowacityrobotics.roboed.impl.subsystem;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;

/** 
 * @author Evan Geng
 */
public abstract class FRCSubsystem<I, O> implements ISubsystem<I, O> {

    private final ISubsystemType<I, O, ? extends ISubsystemProvider<I, O>> type;
    
    protected <P extends ISubsystemProvider<I, O>> FRCSubsystem(ISubsystemType<I, O, P> type) {
        this.type = type;
    }
    
    @Override
    public final ISubsystemType<I, O, ? extends ISubsystemProvider<I, O>> getType() {
        return type;
    }
    
    @Override
    public void reset() {
        // NO-OP
    }
    
}
