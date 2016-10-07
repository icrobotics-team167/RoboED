package org.iowacityrobotics.roboed.frcimpl.sensor;

import org.iowacityrobotics.roboed.api.sensor.ISensor;
import org.iowacityrobotics.roboed.api.sensor.ISensorRegistry;
import org.iowacityrobotics.roboed.api.sensor.SensorType;
import org.iowacityrobotics.roboed.util.primitive.IntTMap;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCSensorRegistry implements ISensorRegistry {

    private final FRCSensorProvider provider;
    private final IntTMap<FRCSensor<?>> registry;

    public FRCSensorRegistry() {
        this.provider = new FRCSensorProvider(DriverStation.getInstance());
        this.registry = new IntTMap<>();
    }

    @Override
    public <T> ISensor<T> put(int port, SensorType<T> type) {
        FRCSensor<T> sensor = provider.get(port, type);
        registry.put(sensor.port(), sensor);
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
