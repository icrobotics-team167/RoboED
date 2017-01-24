package org.iowacityrobotics.roboed.impl.subsystem;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;
import org.iowacityrobotics.roboed.api.subsystem.provider.Providing;

import java.lang.reflect.Method;

/**
 * @author Evan Geng
 */
public class FRCSysProviderProxy implements MethodInterceptor {

    private final FRCSysRegistry registry;
    private final ISubsystemProvider provider;

    public FRCSysProviderProxy(FRCSysRegistry registry, ISubsystemProvider provider) {
        this.registry = registry;
        this.provider = provider;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object returnVal = proxy.invokeSuper(provider, args);
        if (method.isAnnotationPresent(Providing.class))
            registry.registerSubsystem((ISubsystem)returnVal);
        return returnVal;
    }

}
