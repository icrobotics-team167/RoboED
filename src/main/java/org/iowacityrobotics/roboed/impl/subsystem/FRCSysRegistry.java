package org.iowacityrobotics.roboed.impl.subsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.ISystemRegistry;

/** 
 * @author Evan Geng
 */
public class FRCSysRegistry implements ISystemRegistry {
    
    private final Map<ISubsystemType<?, ?>, List<ISubsystem<?, ?>>> registry = new HashMap<>();

    @Override
    public <I, O> ISubsystem<I, O> getSubsystem(ISubsystemType<I, O> type, int id) {
        return null; // TODO Implement
    }

    @Override
    public <I, O> int registerSubsystem(ISubsystem<I, O> system) {
        List<ISubsystem<?, ?>> ofSubsys = registry.get(system.getType());
        if (ofSubsys == null) {
            ofSubsys = new ArrayList<>();
            registry.put(system.getType(), ofSubsys);
        }
        ofSubsys.add(system);
        return ofSubsys.size() - 1;
    }
    
    public void tick() {
        // TODO Implement
    }

}
