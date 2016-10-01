package org.iowacityrobotics.roboed.frcimpl.sensor;

import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.api.sensor.ISensor;
import org.iowacityrobotics.roboed.api.sensor.ISensorRegistry;
import org.iowacityrobotics.roboed.frcimpl.actuator.FRCSensorProvider;
import org.iowacityrobotics.roboed.util.primitive.IntTMap;

public class FRCSensorRegistry implements ISensorRegistry {

    private final FRCSensorProvider provider;
    private final IntTMap<FRCSensor<?>> registry;

    public FRCSensorRegistry() {
        this.provider = new FRCSensorProvider(DriverStation.getInstance());
        this.registry = new IntTMap<>();
    }

    @Override
    public <T> ISensor<T> put(int id, String type) {
        FRCSensor<T> sensor = provider.get(id, type);
        registry.put(sensor.id(), sensor);
        return sensor;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> ISensor<T> get(int id) {
        return (ISensor<T>)registry.get(id);
    }
    
    public void tick() {
        registry.forEach((i, s) -> s.tick());
    }

}
