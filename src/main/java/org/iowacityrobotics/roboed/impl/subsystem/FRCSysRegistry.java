package org.iowacityrobotics.roboed.impl.subsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.ISystemRegistry;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.impl.MecanumSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.impl.SingleJoySubsystem;

/** 
 * @author Evan Geng
 */
public class FRCSysRegistry implements ISystemRegistry {

    private final Map<ISubsystemType<?, ?, ?>, ISubsystemProvider<?, ?>> providerMap = new HashMap<>();
    private final Map<ISubsystemType<?, ?, ?>, List<FRCSubsystem<?, ?>>> registry = new HashMap<>();
    private int firstUnusedId = 0;
    
    public FRCSysRegistry() {
        providerMap.put(MecanumSubsystem.TYPE, new MecanumSubsystem.Provider(this));
        providerMap.put(SingleJoySubsystem.TYPE, new SingleJoySubsystem.Provider(this));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I, O, P extends ISubsystemProvider<I, O>> FRCSubsystem<I, O> getSubsystem(ISubsystemType<I, O, P> type, int id) {
        List<FRCSubsystem<?, ?>> ofSubsys = registry.get(type);
        return (ofSubsys == null || id >= ofSubsys.size() || id < 0) ? null : (FRCSubsystem<I, O>)ofSubsys.get(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I, O, P extends ISubsystemProvider<I, O>> P getProvider(ISubsystemType<I, O, P> type) {
        return (P)providerMap.get(type);
    }
    
    <I, O> int registerSubsystem(FRCSubsystem<I, O> system) {
        List<FRCSubsystem<?, ?>> ofSubsys = registry.get(system.getType());
        if (ofSubsys == null) {
            ofSubsys = new ArrayList<>();
            registry.put(system.getType(), ofSubsys);
        }
        ofSubsys.add(system);
        return ofSubsys.size() - 1;
    }
    
    @SuppressWarnings("rawtypes")
    public void tick() {
        registry.values().stream().flatMap(List::stream)
        .filter(s -> s instanceof FRCTerminalSubsystem)
        .map(s -> (FRCTerminalSubsystem)s)
        .forEach(FRCTerminalSubsystem::tick);
    }
    
    public int nextUnusedId() {
        return ++firstUnusedId - 1;
    }

}
