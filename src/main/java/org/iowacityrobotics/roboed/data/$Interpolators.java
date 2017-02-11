package org.iowacityrobotics.roboed.data;

import org.iowacityrobotics.roboed.util.collection.Pair;
import org.iowacityrobotics.roboed.util.math.Vector2;

import java.util.function.BiFunction;

/**
 * @author Evan Geng
 */
public class $Interpolators {

    public static BiFunction<Pair<Vector2, Vector2>, Double, Pair<Vector2, Vector2>> throttle() {
        return (p, t) -> {
            p.getA().x(p.getA().x() * t).y(p.getA().y() * t);
            p.getB().x(p.getB().x() * t).y(p.getB().y() * t);
            return p;
        };
    }

}
