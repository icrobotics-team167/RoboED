package org.iowacityrobotics.roboed.frcimpl.actuator;

import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.api.actuator.ActuatorType;
import org.iowacityrobotics.roboed.frcimpl.actuator.impl.FRCCANMotor;
import org.iowacityrobotics.roboed.frcimpl.actuator.impl.FRCPWMMotor;
import org.iowacityrobotics.roboed.util.primitive.IIntTBiFunction;

import java.util.HashMap;
import java.util.Map;

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
        this.typeMap.put("a_motor_can_cont", FRCCANMotor::new);
        this.typeMap.put("a_motor_pwm_cont", FRCPWMMotor::new);
    }

    @SuppressWarnings("unchecked")
    public <T> FRCActuator<T> get(int port, ActuatorType<T> type) {
        return (FRCActuator<T>)typeMap.get(type.name()).apply(port, ds);
    }

}
