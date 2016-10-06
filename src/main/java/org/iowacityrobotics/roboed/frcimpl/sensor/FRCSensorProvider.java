package org.iowacityrobotics.roboed.frcimpl.sensor;

import java.util.HashMap;
import java.util.Map;

import org.iowacityrobotics.roboed.api.sensor.SensorType;
import org.iowacityrobotics.roboed.util.primitive.IIntTBiFunction;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCSensorProvider {

    private final DriverStation ds;
    private final Map<String, IIntTBiFunction<DriverStation, FRCSensor<?>>> typeMap;

    public FRCSensorProvider(DriverStation ds) {
        this.ds = ds;
        this.typeMap = new HashMap<>(); // TODO Implement actuator types
    }

    @SuppressWarnings("unchecked")
    public <T> FRCSensor<T> get(int id, SensorType<T> type) {
        return (FRCSensor<T>)typeMap.get(type.name()).apply(id, ds);
    }

}
