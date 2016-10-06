package org.iowacityrobotics.roboed.frcimpl.actuator;

import java.util.HashMap;
import java.util.Map;

import org.iowacityrobotics.roboed.api.actuator.ActuatorType;
import org.iowacityrobotics.roboed.util.primitive.IIntTBiFunction;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCActuatorProvider {

    private final DriverStation ds;
    private final Map<String, IIntTBiFunction<DriverStation, FRCActuator<?>>> typeMap;

    public FRCActuatorProvider(DriverStation ds) {
        this.ds = ds;
        this.typeMap = new HashMap<>(); // TODO Implement actuator types
    }

    @SuppressWarnings("unchecked")
    public <T> FRCActuator<T> get(int id, ActuatorType<T> type) {
        return (FRCActuator<T>)typeMap.get(type.name()).apply(id, ds);
    }

}
