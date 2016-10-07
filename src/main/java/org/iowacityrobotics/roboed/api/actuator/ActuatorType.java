package org.iowacityrobotics.roboed.api.actuator;

/**
 * Represents a type of actuator.
 * @author Evan Geng
 * @param <T> The data type accepted by this actuator.
 */
public class ActuatorType<T> {

    /**
     * Represents a simple analog output of some unknown type.
     */
    public static final ActuatorType<Double> ANALOG = new ActuatorType<>("a_analog");

    /**
     * Represents a continuous motor on the CAN bus. Accepts the speed to output.
     */
    public static final ActuatorType<Double> CAN_MOTOR = new ActuatorType<>("a_motor_can_cont");

    /**
     * Represents a servo motor on the CAN bus. Accepts the position to move to.
     */
    public static final ActuatorType<Double> CAN_SERVO = new ActuatorType<>("a_motor_can_servo");

    /**
     * Represents a continuous motor on the PWM rail. Accepts the speed to output.
     */
    public static final ActuatorType<Double> PWM_MOTOR = new ActuatorType<>("a_motor_pwm_cont");

    /**
     * Represents a servo motor on the PWM rail. Accepts the position to move to.
     */
    public static final ActuatorType<Double> PWM_SERVO = new ActuatorType<>("a_motor_pwm_servo");

    /**
     * Represents an electromechanical solenoid. Accepts the solenoid's state.
     */
    public static final ActuatorType<Boolean> SOLENOID = new ActuatorType<>("a_solenoid");

    /**
     * Represents a pneumatic cylinder. Accepts the extension state of the cylinder.
     */
    public static final ActuatorType<Boolean> PISTON = new ActuatorType<>("a_piston");

    /**
     * The name of this device.
     */
    private final String name;
    
    /**
     * Creates a new ActuatorType with the given name.
     * @param name The actuator's name.
     */
    public ActuatorType(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name of this device.
     * @return The device name.
     */
    public String name() {
        return name;
    }
    
}
