package org.iowacityrobotics.roboed.subsystem;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.sink.ISink;
import org.iowacityrobotics.roboed.robot.Devices;
import org.iowacityrobotics.roboed.util.math.Vector2;
import org.iowacityrobotics.roboed.util.math.Vector4;
import org.iowacityrobotics.roboed.util.robot.MotorTuple4;

/**
 * Data sinks representing various physical subsystems.
 * @author Evan Geng
 */
public enum SinkSystems {

    /**
     * Motor-related subsystems.
     */
    MOTOR {

        /**
         * Creates a sink for the given CANTalon.
         * @param port The CANTalon's port number.
         * @return The new sink.
         */
        public final ISink<Double> canTalon(int port) {
            final CANTalon motor = Devices.canTalon(port);
            return Data.sink(motor::set);
        }

    },

    /**
     * Drive-train-related subsystems.
     */
    DRIVE {

        /**
         * Creates a dual-tread tank drive sink using {@link CANTalon}s.
         * @param fl The front-left talon's ID.
         * @param rl The rear-left talon's ID.
         * @param fr The front-right talon's ID.
         * @param rr The rear-right talon's ID.
         * @return The new sink.
         */
        public final ISink<Vector2> dualTread(int fl, int rl, int fr, int rr) {
            return dualTread(MotorTuple4.ofCANTalons(fl, rl, fr, rr));
        }

        /**
         * Creates a dual-tread tank drive sink.
         * @param m The four speed controllers.
         * @return The new sink.
         */
        public final ISink<Vector2> dualTread(MotorTuple4 m) {
            final RobotDrive drive = new RobotDrive(
                    m.getFrontLeft(),
                    m.getFrontRight(),
                    m.getRearLeft(),
                    m.getRearRight()
            );
            return Data.sink(v -> drive.tankDrive(v.x(), v.y()));
        }

        /**
         * Creates a 4-wheel mecanum drive sink using {@link CANTalon}s.
         * @param fl The front-left talon's ID.
         * @param rl The rear-left talon's ID.
         * @param fr The front-right talon's ID.
         * @param rr The rear-right talon's ID.
         * @return The new sink.
         */
        public final ISink<Vector4> mecanum(int fl, int rl, int fr, int rr) {
            return mecanum(MotorTuple4.ofCANTalons(fl, rl, fr, rr));
        }

        /**
         * Creates a 4-wheel mecanum drive sink.
         * @param m The four speed controllers.
         * @return The new sink.
         */
        public final ISink<Vector4> mecanum(MotorTuple4 m) {
            final RobotDrive drive = new RobotDrive(
                    m.getFrontLeft(),
                    m.getFrontRight(),
                    m.getRearLeft(),
                    m.getRearRight()
            );
            return Data.sink(v -> drive.mecanumDrive_Cartesian(v.x(), v.y(), v.z(), v.w()));
        }

    }

}
