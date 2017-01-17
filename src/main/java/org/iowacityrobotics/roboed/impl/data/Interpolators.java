package org.iowacityrobotics.roboed.impl.data;

import org.iowacityrobotics.roboed.util.collection.Pair;
import org.iowacityrobotics.roboed.util.math.Vector2;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

/**
 * @author Evan Geng
 */
public class Interpolators {

    public static Function<Pair<Vector2, Vector2>, Pair<Vector2, Vector2>> throttle(DoubleSupplier throttleFunc) {
        return p -> {
            double throttle = throttleFunc.getAsDouble();
            p.getA().x(p.getA().x() * throttle).y(p.getA().y() * throttle);
            p.getB().x(p.getB().x() * throttle).y(p.getB().y() * throttle);
            return p;
        };
    }

}
