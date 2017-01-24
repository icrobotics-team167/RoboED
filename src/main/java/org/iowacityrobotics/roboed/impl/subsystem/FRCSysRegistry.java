package org.iowacityrobotics.roboed.impl.subsystem;

import net.sf.cglib.proxy.Enhancer;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.ISystemRegistry;
import org.iowacityrobotics.roboed.api.subsystem.ITerminalSubsystem;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.impl.*;

import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/** 
 * @author Evan Geng
 */
public class FRCSysRegistry implements ISystemRegistry { // TODO A way to register custom subsystems

    private final Map<ISubsystemType<?, ?, ?>, ISubsystemProvider<?, ?>> providerMap = new HashMap<>();
    private final Collection<ISubsystem<?, ?>> registry = new HashSet<>();
    private final Collection<ITerminalSubsystem<?>> termRegistry = new HashSet<>();
    
    public FRCSysRegistry() {
        registerProvider(MecanumSubsystem.TYPE, new MecanumSubsystem.Provider());
        registerProvider(MecanumSubsystem.TYPE_CUSTOM, new MecanumSubsystem.CustomProvider());
        registerProvider(DualTreadSubsystem.TYPE, new DualTreadSubsystem.Provider());
        registerProvider(DualTreadSubsystem.TYPE_CUSTOM, new DualTreadSubsystem.CustomProvider());
        registerProvider(CameraSubsystem.TYPE, new CameraSubsystem.Provider());
        registerProvider(SingleJoySubsystem.TYPE, new SingleJoySubsystem.Provider());
        registerProvider(DualJoySubsystem.TYPE, new DualJoySubsystem.Provider());
        registerProvider(VisionOffloaderSubsystem.TYPE, new VisionOffloaderSubsystem.Provider());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I, O, P extends ISubsystemProvider<I, O>> P getProvider(ISubsystemType<I, O, P> type) {
        return (P)providerMap.get(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I, O, P extends ISubsystemProvider<I, O>> void registerProvider(ISubsystemType<I, O, P> type, P provider) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(provider.getClass());
        enhancer.setCallback(new FRCSysProviderProxy(this, provider));
        providerMap.put(type, (ISubsystemProvider<I, O>)enhancer.create());
    }

    public void registerSubsystem(ISubsystem system) {
        registry.add(system);
        if (system instanceof ITerminalSubsystem)
            termRegistry.add((ITerminalSubsystem)system);
    }
    
    @SuppressWarnings("rawtypes")
    public void tick() {
        termRegistry.forEach(ITerminalSubsystem::tick);
    }

    public void reset() {
        registry.forEach(ISubsystem::reset);
    }

}
