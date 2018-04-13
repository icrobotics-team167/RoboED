package org.iowacityrobotics.roboed.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.sink.Sink;
import org.iowacityrobotics.roboed.robot.Devices;
import org.iowacityrobotics.roboed.util.math.Vector2;
import org.iowacityrobotics.roboed.util.math.Vector4;
import org.iowacityrobotics.roboed.util.robot.MotorTuple4;

/**
 * Data sinks representing various physical subsystems.
 * @author Evan Geng
 */
public final class SinkSystems {

    /**
     * Motor-related subsystems.
     */
    public static final class MOTOR {

        /**
         * Creates a sink for the given TalonSRX.
         * @param port The TalonSRX's port number.
         * @return The new sink.
         */
        public static Sink<Double> talonSrx(int port) {
            final WPI_TalonSRX motor = Devices.talonSrx(port);
            return Data.sink(motor::set, 0D);
        }

        /**
         * Creates a sink for the given VictorSP.
         * @param port The VictorSP port number.
         * @return The new sink.
         */
        public static Sink<Double> victorSp(int port) {
            final VictorSP motor = Devices.victorSp(port);
            return Data.sink(motor::set, 0D);
        }

        /**
         * Creates a sink for the given Spark.
         * @param port The Spark's PWM port.
         * @return The new sink.
         */
        public static Sink<Double> spark(int port) {
            final Spark motor = Devices.spark(port);
            return Data.sink(motor::set, 0D);
        }

        /**
         * Creates a sink for the given Servo.
         * @param port The Servo's port number.
         * @return The new sink.
         */
        public static Sink<Double> servo(int port) {
            return servo(port, 0D);
        }

        /**
         * Creates a sink for the given Servo with a default setpoint.
         * @param port The Servo's port number.
         * @param def The default setpoint.
         * @return The new sink.
         */
        public static Sink<Double> servo(int port, double def) {
            final Servo servo = Devices.servo(port);
            return Data.sink(servo::set, def);
        }

    }

    /**
     * Other actuators.
     */
    public static final class OTHER {

        /**
         * Creates a sink for the given pair of solenoids.
         * @param portFwd The forwards solenoid's port.
         * @param portRev The reverse solenoid's port.
         * @return The new sink.
         */
        public static Sink<DoubleSolenoid.Value> dblSolenoid(int portFwd, int portRev) {
            final DoubleSolenoid solenoid = Devices.dblSolenoid(portFwd, portRev);
            return Data.sink(solenoid::set, DoubleSolenoid.Value.kOff);
        }

    }

    /**
     * Drive-train-related subsystems.
     */
    public static final class DRIVE {

        /**
         * Creates a dual-tread tank drive sink using {@link WPI_TalonSRX}s.
         * @param fl The front-left talon's ID.
         * @param rl The rear-left talon's ID.
         * @param fr The front-right talon's ID.
         * @param rr The rear-right talon's ID.
         * @return The new sink.
         */
        public static Sink<Vector2> dualTread(int fl, int rl, int fr, int rr) {
            return dualTread(MotorTuple4.ofTalons(fl, rl, fr, rr));
        }

        /**
         * Creates a dual-tread tank drive sink.
         * @param m The four speed controllers.
         * @return The new sink.
         */
        public static Sink<Vector2> dualTread(MotorTuple4 m) {
            final DifferentialDrive drive = new DifferentialDrive(
                    new SpeedControllerGroup(m.getFrontLeft(), m.getRearLeft()),
                    new SpeedControllerGroup(m.getFrontRight(), m.getRearRight())
            );
            return Data.sink(v -> drive.tankDrive(v.x(), v.y()), Vector2.ZERO);
        }

        /**
         * Creates a 4-wheel mecanum drive sink using {@link WPI_TalonSRX}s.
         * @param fl The front-left talon's ID.
         * @param rl The rear-left talon's ID.
         * @param fr The front-right talon's ID.
         * @param rr The rear-right talon's ID.
         * @return The new sink.
         */
        public static Sink<Vector4> mecanum(int fl, int rl, int fr, int rr) {
            return mecanum(MotorTuple4.ofTalons(fl, rl, fr, rr));
        }

        /**
         * Creates a 4-wheel mecanum drive sink.
         * @param m The four speed controllers.
         * @return The new sink.
         */
        public static Sink<Vector4> mecanum(MotorTuple4 m) {
            final MecanumDrive drive = new MecanumDrive(
                    m.getFrontLeft(),
                    m.getRearLeft(),
                    m.getFrontRight(),
                    m.getRearRight()
            );
            return Data.sink(v -> drive.driveCartesian(v.x(), -v.y(), v.z(), v.w()), Vector4.ZERO);
        }

    }

    /**
     * Smart-dashboard-related subsystems.
     */
    public static final class DASH {

        /**
         * Creates a sink that outputs numeral data to Smart Dashboard.
         * @param key The table entry key.
         * @return The new sink.
         */
        public static Sink<Double> number(String key) {
            return Data.sink(n -> {
                if (n != null) SmartDashboard.putNumber(key, n);
            });
        }

        /**
         * Creates a sink that outputs textual data to Smart Dashboard.
         * @param key The table entry key.
         * @return The new sink.
         */
        public static Sink<String> string(String key) {
            return Data.sink(s -> {
                if (s != null) SmartDashboard.putString(key, s);
            });
        }

    }

}
