package org.iowacityrobotics.roboed.subsystem;

import com.mashape.unirest.http.JsonNode;
import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.mapper.Mapper;
import org.iowacityrobotics.roboed.subsystem.impl.ToggleMapper;
import org.iowacityrobotics.roboed.subsystem.impl.VisionOffloaderMapper;
import org.iowacityrobotics.roboed.util.collection.Pair;
import org.iowacityrobotics.roboed.util.math.Vector2;
import org.iowacityrobotics.roboed.util.math.Vector4;
import org.opencv.core.Mat;

/**
 * Data mappers representing various physical subsystems.
 * @author Evan Geng
 */
public final class MapperSystems {

    /**
     * Control-related subsystems.
     */
    public static final class CONTROL {

        /**
         * Creates a boolean control toggling mapper.
         * @return The new mapper.
         */
        public static Mapper<Boolean, Boolean> toggle() {
            return new ToggleMapper();
        }

        /**
         * Creates a boolean-to-double ternary mapper.
         * @param off The value for the <code>false</code> state.
         * @param on The value for the <code>true</code> state.
         * @param <T> The value data type.
         * @return The new mapper.
         */
        public static <T> Mapper<Boolean, T> buttonValue(T off, T on) {
            return Data.mapper(v -> v ? on : off);
        }

        /**
         * Transforms some analog controller input to a boolean.
         * @param thresh The activation threshold.
         * @return The new mapper.
         */
        public static Mapper<Double, Boolean> booleanify(double thresh) {
            return Data.mapper(v -> v >= thresh);
        }

    }

    /**
     * Drivetrain-related subsystems.
     */
    public static final class DRIVE {

        /**
         * Mecanum drive with a single joystick.
         * @return The new mapper.
         */
        public static Mapper<Vector4, Vector4> singleJoyMecanum() {
            return Data.mapper(v -> new Vector4(v.x(), v.y(), v.z(), 0));
        }

        /**
         * Mecanum drive with two joysticks (e.g. an XBox controller).
         * @return The new mapper.
         */
        public static Mapper<Pair<Vector2, Vector2>, Vector4> dualJoyMecanum() {
            return Data.mapper(p -> new Vector4(p.getA().x(), p.getA().y(), p.getB().x(), 0));
        }

    }

    /**
     * Vision-related subsystems.
     */
    public static final class VISION {

        /**
         * Creates a vision offloading mapper.
         * @param host The external processor's hostname.
         * @param routine The routine to run.
         * @return The new mapper.
         */
        public static Mapper<Mat, JsonNode> offload(String host, String routine) {
            return new VisionOffloaderMapper(host, routine);
        }

    }

}
