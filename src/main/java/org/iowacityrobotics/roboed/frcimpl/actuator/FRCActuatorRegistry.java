package org.iowacityrobotics.roboed.frcimpl.actuator;

import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.api.actuator.IActuator;
import org.iowacityrobotics.roboed.api.actuator.IActuatorRegistry;
import org.iowacityrobotics.roboed.util.primitive.IntTMap;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCActuatorRegistry implements IActuatorRegistry {

    private final FRCActuatorProvider provider;
    private final IntTMap<FRCActuator<?>> registry;

    public FRCActuatorRegistry() {
        this.provider = new FRCActuatorProvider(DriverStation.getInstance());
        this.registry = new IntTMap<>();
    }

    @Override
    public <T> IActuator<T> put(int id, String type) {
        FRCActuator<T> actuator = provider.get(id, type);
        registry.put(actuator.id(), actuator);
        return actuator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> IActuator<T> get(int id) {
        return (IActuator<T>)registry.get(id);
    }

}
