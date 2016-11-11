package org.iowacityrobotics.roboed.impl.data;

import java.util.function.Function;

import org.iowacityrobotics.roboed.impl.subsystem.impl.MecanumSubsystem;
import org.iowacityrobotics.roboed.util.math.Vector3;

/**
 * @author Evan Geng
 */
public class DataMappers {

    public static Function<Vector3, MecanumSubsystem.ControlDataFrame> singleJoyMecanum() {
        return v -> new MecanumSubsystem.ControlDataFrame(v.truncate(), v.z());
    }
    
}
