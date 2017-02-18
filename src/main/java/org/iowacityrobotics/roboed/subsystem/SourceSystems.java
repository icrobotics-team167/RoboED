package org.iowacityrobotics.roboed.subsystem;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import org.iowacityrobotics.roboed.data.source.ISource;
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
        public final ISource<Vector4> singleJoy(int port) {
            final Joystick joy = Devices.joystick(port);
            return () -> new Vector4(
                    joy.getAxis(Joystick.AxisType.kX),
                    joy.getAxis(Joystick.AxisType.kY),
                    joy.getAxis(Joystick.AxisType.kTwist),
                    joy.getAxis(Joystick.AxisType.kThrottle)
            );
        }

        /**
         * Creates a source for the given XBox controller.
         * @param port The controller port.
         * @return The new source.
         */
        public final ISource<Pair<Vector2, Vector2>> dualJoy(int port) {
            final XboxController cont = Devices.xboxController(port);
            return () -> Pair.of(
                    new Vector2(cont.getX(GenericHID.Hand.kLeft), cont.getY(GenericHID.Hand.kLeft)),
                    new Vector2(cont.getX(GenericHID.Hand.kRight), cont.getY(GenericHID.Hand.kRight))
            );
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
        public final ISource<Double> ultrasonic(int ping, int echo) {
            final Ultrasonic sensor = new Ultrasonic(ping, echo);
            return sensor::getRangeMM;
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
        public final ISource<Mat> imageStream(Supplier<Mat> source) {
            return source::get;
        }

    }

}
