package org.iowacityrobotics.roboed.impl.subsystem;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;
import org.iowacityrobotics.roboed.api.subsystem.provider.Providing;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Evan Geng
 */
public class FRCSysProviderProxy implements InvocationHandler {

    private final FRCSysRegistry registry;
    private final ISubsystemProvider provider;

    public FRCSysProviderProxy(FRCSysRegistry registry, ISubsystemProvider provider) {
        this.registry = registry;
        this.provider = provider;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnVal = method.invoke(provider, args);
        if (method.isAnnotationPresent(Providing.class))
            registry.registerSubsystem((ISubsystem)returnVal);
        return returnVal;
    }

}
