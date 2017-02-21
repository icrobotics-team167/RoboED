package org.iowacityrobotics.roboed.subsystem;

import edu.wpi.first.wpilibj.*;
import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.source.Source;
import org.iowacityrobotics.roboed.robot.Devices;
import org.iowacityrobotics.roboed.util.collection.Pair;
import org.iowacityrobotics.roboed.util.math.Vector2;
import org.iowacityrobotics.roboed.util.math.Vector4;
import org.opencv.core.Mat;

import java.util.function.Supplier;

/**
 * Data sources representing various physical subsystems.
 * @author Evan Geng
 */
public final class SourceSystems {

    /**
     * Controller-related subsystems.
     */
    public static final class CONTROL {

        /**
         * Creates a source for the given Joystick.
         * @param port The joystick's port.
         * @return The new source.
         */
        public static Source<Vector4> singleJoy(int port) {
            final Joystick joy = Devices.joystick(port);
            return Data.source(() -> new Vector4(
                    joy.getAxis(Joystick.AxisType.kX),
                    joy.getAxis(Joystick.AxisType.kY),
                    joy.getAxis(Joystick.AxisType.kTwist),
                    joy.getAxis(Joystick.AxisType.kThrottle)
            ));
        }

        /**
         * Creates a source for the given XBox controller.
         * @param port The controller port.
         * @return The new source.
         */
        public static Source<Pair<Vector2, Vector2>> dualJoy(int port) {
            final XboxController cont = Devices.xboxController(port);
            return Data.source(() -> Pair.of(
                    new Vector2(cont.getX(GenericHID.Hand.kLeft), cont.getY(GenericHID.Hand.kLeft)),
                    new Vector2(cont.getX(GenericHID.Hand.kRight), cont.getY(GenericHID.Hand.kRight))
            ));
        }

        /**
         * Creates a source for the given button.
         * @param port The controller port.
         * @param button The button ID.
         * @return The new source.
         */
        public static Source<Boolean> button(int port, int button) {
            return Data.source(() -> DriverStation.getInstance().getStickButton(port, (byte)button));
        }

    }

    /**
     * Sensor-related subsystems.
     */
    public static final class SENSOR {

        /**
         * Creates a source for the given ultrasonic sensor.
         * @param ping The ping signal port.
         * @param echo The echo signal port.
         * @return The new source.
         */
        public static Source<Double> ultrasonic(int ping, int echo) {
            final Ultrasonic sensor = new Ultrasonic(ping, echo);
            return Data.source(sensor::getRangeMM);
        }

    }

    /**
     * Vision-related subsystems.
     */
    public static final class VISION {

        /**
         * Creates a source for the given image stream.
         * @param source The image stream.
         * @return The new source.
         */
        public static Source<Mat> imageStream(Supplier<Mat> source) {
            return Data.source(source);
        }

    }

}
