package org.iowacityrobotics.roboed.impl.subsystem;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.ISystemRegistry;

/** 
 * @author Evan Geng
 */
public class FRCSysRegistry implements ISystemRegistry {

    @Override
    public <I, O> ISubsystem<I, O> getSubsystem(ISubsystemType<I, O> type, int id) {
        return null; // TODO Implement
    }

    @Override
    public <I, O> int registerSubsystem(ISubsystem<I, O> system) {
        return -1; // TODO Implement
    }
    
    public void tick() {
        // TODO Implement
    }

}
