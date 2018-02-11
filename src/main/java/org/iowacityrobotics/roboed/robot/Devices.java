package org.iowacityrobotics.roboed.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import org.iowacityrobotics.roboed.util.primitive.IntTMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;

/**
 * Manages devices with a port number so WPILib doesn't complain about conflicts.
 * @author Evan Geng
 */
public class Devices {

    /**
     * The devices registry.
     */
    private static final Map<Class<?>, IntTMap<Object>> registry = new HashMap<>();

    /**
     * Retrieves a previously registered device.
     * @param type A {@link Class} object representing the device's type..
     * @param port The device's port number.
     * @param <T> The device's type.
     * @return The device.
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> type, int port) {
        if (!registry.containsKey(type))
            return null;
        return (T)registry.get(type).get(port);
    }

    /**
     * Retrieves a device, or registers it if it hasn't already been registered.
     * @param type A {@link Class} object representing the device's type.
     * @param port The device's port number.
     * @param factory A factory function mapping port numbers to device instances.
     * @param <T> The device's type.
     * @return The device.
     */
    @SuppressWarnings("unchecked")
    public static <T> T compute(Class<T> type, int port, IntFunction<T> factory) {
        return (T)registry.computeIfAbsent(type, ignored -> new IntTMap<>())
                .computeIfAbsent(port, factory::apply);
    }

    /**
     * Creates or gets a {@link WPI_TalonSRX} for the given port number.
     * @param port The controller's port number.
     * @return The TalonSRX.
     */
    public static WPI_TalonSRX talonSrx(int port) {
        return compute(WPI_TalonSRX.class, port, WPI_TalonSRX::new);
    }

    /**
     * Creates or gets a {@link VictorSP} for the given port number.
     * @param port The controller's port number.
     * @return The VictorSP.
     */
    public static VictorSP victorSp(int port) {
        return compute(VictorSP.class, port, VictorSP::new);
    }

    /**
     * Creates or gets an {@link XboxController} for the given port number.
     * @param port The controller's port number.
     * @return The XboxController.
     */
    public static XboxController xboxController(int port) {
        return compute(XboxController.class, port, XboxController::new);
    }

    /**
     * Creates or gets a {@link Joystick} for the given port number.
     * @param port The joystick's port number.
     * @return The Joystick.
     */
    public static Joystick joystick(int port) {
        return compute(Joystick.class, port, Joystick::new);
    }

    /**
     * Creates or gets a {@link Servo} for the given port number.
     * @param port The servo's port number.
     * @return The servo.
     */
    public static Servo servo(int port) {
        return compute(Servo.class, port, Servo::new);
    }

    /**
     * Creates or gets an {@link AnalogInput} for the given port number.
     * @param port The device's port number.
     * @return The analog input.
     */
    public static AnalogInput analogInput(int port) {
        return compute(AnalogInput.class, port, AnalogInput::new);
    }

    /**
     * Creates or gets a {@link DoubleSolenoid} for the given port numbers.
     * @param portFwd The forwards solenoid's port number.
     * @param portRev The reverse solenoid's port number.
     * @return The double solenoid.
     */
    public static DoubleSolenoid dblSolenoid(int portFwd, int portRev) {
        IntTMap<Object> solenoids = registry.computeIfAbsent(FakeDoubleSolenoidHalf.class, ignored -> new IntTMap<>());
        if (solenoids.contains(portFwd) && solenoids.contains(portRev))
            return ((FakeDoubleSolenoidHalf)solenoids.get(portFwd)).parent;
        if (solenoids.contains(portFwd) || solenoids.contains(portRev))
            throw new IllegalArgumentException("You're dumb!!! Don't make two solenoids with conflicting port numbers!!!");
        DoubleSolenoid solenoid = new DoubleSolenoid(portFwd, portRev);
        FakeDoubleSolenoidHalf fake = new FakeDoubleSolenoidHalf(solenoid);
        solenoids.put(portFwd, fake);
        solenoids.put(portRev, fake);
        return solenoid;
    }

    /**
     * Placeholder class for making {@link DoubleSolenoid} construction work.
     */
    private static class FakeDoubleSolenoidHalf {

        final DoubleSolenoid parent;

        FakeDoubleSolenoidHalf(DoubleSolenoid parent) {
            this.parent = parent;
        }

    }

    /**
     * Creates or gets a {@link DigitalInput} for the given DIO port number.
     * @param port The DIO device's port number.
     * @return The newly-created DIO input.
     */
    public static DigitalInput dioInput(int port) {
        return compute(DigitalInput.class, port, DigitalInput::new);
    }
    
}
