package org.iowacityrobotics.roboed.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
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
     * Creates or gets a {@link CANTalon} for the given port number.
     * @param port The controller's port number.
     * @return The CANTalon.
     */
    public static CANTalon canTalon(int port) {
        return compute(CANTalon.class, port, CANTalon::new);
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

}