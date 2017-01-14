package org.iowacityrobotics.roboed.impl.data;

import java.util.function.BiFunction;
import java.util.function.DoubleSupplier;
import java.util.function.Function;

import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.impl.subsystem.impl.DualTreadSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.impl.MecanumSubsystem;
import org.iowacityrobotics.roboed.util.collection.Pair;
import org.iowacityrobotics.roboed.util.math.Vector2;
import org.iowacityrobotics.roboed.util.math.Vector3;

/**
 * @author Evan Geng
 */
public class DataMappers {

    public static Function<Vector3, MecanumSubsystem.ControlDataFrame> singleJoyMecanum() {
        return v -> new MecanumSubsystem.ControlDataFrame(v.truncate(), v.z());
    }

    public static Function<Pair<Vector2, Vector2>, DualTreadSubsystem.ControlDataFrame> dualJoyTank() {
        return p -> new DualTreadSubsystem.ControlDataFrame(p.getA().y(), p.getB().y());
    }

    public static Function<Pair<Vector2, Vector2>, Pair<Vector2, Vector2>> throttle(DoubleSupplier throttleFunc) {
        return p -> {
            double throttle = throttleFunc.getAsDouble();
            p.getA().x(p.getA().x() * throttle).y(p.getA().y() * throttle);
            p.getB().x(p.getB().x() * throttle).y(p.getB().y() * throttle);
            return p;
        };
    }
    
    public static Function<Pair<Vector2, Vector2>, Pair<Vector2, Vector2>> invert() {
        return p -> {
            p.getA().x(-p.getA().x()).y(-p.getA().y());
            p.getB().x(-p.getB().x()).y(-p.getB().y());
            return p;
        };
    }
    
}
