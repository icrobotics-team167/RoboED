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
    
    public static Function<Pair<Vector2, Vector2>, Pair<Vector2, Vector2>> invert() {
        return p -> {
            p.getA().x(-p.getA().x()).y(-p.getA().y());
            p.getB().x(-p.getB().x()).y(-p.getB().y());
            return p;
        };
    }

    public static Function<Vector3, Vector3> deadZone(double threshold) {
        return v -> {
            if (Math.abs(v.x()) <= threshold)
                v.x(0);
            if (Math.abs(v.y()) <= threshold)
                v.y(0);
            if (Math.abs(v.z()) <= threshold)
                v.z(0);
            return v;
        };
    }
    
}
