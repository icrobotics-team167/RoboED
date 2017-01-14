package org.iowacityrobotics.roboed.impl.subsystem;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.ISystemRegistry;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.impl.*;
import org.iowacityrobotics.roboed.util.primitive.IntTMap;
import org.iowacityrobotics.roboed.util.primitive.IntTPair;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author Evan Geng
 */
public class FRCSysRegistry implements ISystemRegistry { // TODO A way to register custom subsystems

    private final Map<ISubsystemType<?, ?, ?>, ISubsystemProvider<?, ?>> providerMap = new HashMap<>();
    private final Map<ISubsystemType<?, ?, ?>, IntTMap<FRCSubsystem<?, ?>>> registry = new HashMap<>();
    private int firstUnusedId = 0;
    
    public FRCSysRegistry() {
        providerMap.put(MecanumSubsystem.TYPE, new MecanumSubsystem.Provider(this));
        providerMap.put(MecanumSubsystem.TYPE_CUSTOM, new MecanumSubsystem.CustomProvider(this));
        providerMap.put(DualTreadSubsystem.TYPE, new DualTreadSubsystem.Provider(this));
        providerMap.put(DualTreadSubsystem.TYPE_CUSTOM, new DualTreadSubsystem.CustomProvider(this));
        providerMap.put(CameraSubsystem.TYPE, new CameraSubsystem.Provider(this));
        providerMap.put(SingleJoySubsystem.TYPE, new SingleJoySubsystem.Provider(this));
        providerMap.put(DualJoySubsystem.TYPE, new DualJoySubsystem.Provider(this));
        providerMap.put(VisionOffloaderSubsystem.TYPE, new VisionOffloaderSubsystem.Provider(this));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I, O, P extends ISubsystemProvider<I, O>> FRCSubsystem<I, O> getSubsystem(ISubsystemType<I, O, P> type, int id) {
        IntTMap<FRCSubsystem<?, ?>> ofSubsys = registry.get(type);
        return (ofSubsys == null || id >= ofSubsys.size() || id < 0) ? null : (FRCSubsystem<I, O>)ofSubsys.get(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I, O, P extends ISubsystemProvider<I, O>> P getProvider(ISubsystemType<I, O, P> type) {
        return (P)providerMap.get(type);
    }
    
    public <I, O> FRCSubsystem<I, O> register(FRCSubsystem<I, O> system) {
        IntTMap<FRCSubsystem<?, ?>> ofSubsys = registry.computeIfAbsent(system.getType(), t -> new IntTMap<>());
        ofSubsys.put(system.getId(), system);
        return system;
    }
    
    @SuppressWarnings("rawtypes")
    public void tick() {
        registry.values().stream().flatMap(IntTMap::stream)
                .map(IntTPair::getB)
                .filter(s -> s instanceof FRCTerminalSubsystem)
                .map(s -> (FRCTerminalSubsystem)s)
                .forEach(FRCTerminalSubsystem::tick);
    }

    public void reset() {
        registry.values().forEach(type -> type.forEach((id, sys) -> sys.reset()));
    }

    public int nextUnusedId() {
        return ++firstUnusedId - 1;
    }

}
