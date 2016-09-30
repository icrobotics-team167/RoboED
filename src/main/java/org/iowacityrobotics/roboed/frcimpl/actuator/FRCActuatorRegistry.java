package org.iowacityrobotics.roboed.frcimpl.actuator;

import org.iowacityrobotics.roboed.api.actuator.IActuator;
import org.iowacityrobotics.roboed.api.actuator.IActuatorRegistry;
import org.iowacityrobotics.roboed.util.primitive.IntTMap;

public class FRCActuatorRegistry implements IActuatorRegistry {

	private final IntTMap<IActuator<?>> registry;
	
	public FRCActuatorRegistry() {
		registry = new IntTMap<>();
	}
	
    @Override
    public void put(IActuator<?> actuator) {
        registry.put(actuator.id(), actuator);
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> IActuator<T> get(int id) {
        return (IActuator<T>)registry.get(id);
    }

}
