package org.iowacityrobotics.roboed.frcimpl.sensor;

import org.iowacityrobotics.roboed.api.sensor.ISensor;
import org.iowacityrobotics.roboed.api.sensor.ISensorRegistry;
import org.iowacityrobotics.roboed.util.primitive.IntTMap;

public class FRCSensorRegistry implements ISensorRegistry {

	private final IntTMap<ISensor<?>> registry;
	
	public FRCSensorRegistry() {
		registry = new IntTMap<>();
	}
	
    @Override
    public void put(ISensor<?> sensor) {
        registry.put(sensor.id(), sensor);        
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> ISensor<T> get(int id) {
        return (ISensor<T>)registry.get(id);
    }
    
    public void tick() {
    	// TODO Implement
    }

}
