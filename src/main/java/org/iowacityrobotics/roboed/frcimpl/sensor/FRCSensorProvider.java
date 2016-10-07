package org.iowacityrobotics.roboed.frcimpl.sensor;

import edu.wpi.first.wpilibj.DriverStation;
import org.iowacityrobotics.roboed.api.sensor.SensorType;
import org.iowacityrobotics.roboed.frcimpl.sensor.impl.FRCAnalogSensor;
import org.iowacityrobotics.roboed.frcimpl.sensor.impl.FRCButton;
import org.iowacityrobotics.roboed.frcimpl.sensor.impl.FRCDigitalSensor;
import org.iowacityrobotics.roboed.frcimpl.sensor.impl.FRCJoystick;
import org.iowacityrobotics.roboed.util.primitive.IIntTBiFunction;

import java.util.HashMap;
import java.util.Map;

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
        this.typeMap.put("s_button", FRCButton::new);
        this.typeMap.put("s_analog", FRCAnalogSensor::new);
        this.typeMap.put("s_joystick", FRCJoystick::new);
        this.typeMap.put("s_digital", FRCDigitalSensor::new);
    }

    @SuppressWarnings("unchecked")
    public <T> FRCSensor<T> get(int port, SensorType<T> type) {
        return (FRCSensor<T>)typeMap.get(type.name()).apply(port, ds);
    }

}
