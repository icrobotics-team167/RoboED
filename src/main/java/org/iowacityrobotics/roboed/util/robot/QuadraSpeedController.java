package org.iowacityrobotics.roboed.util.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Represents a set of four speed controllers.
 * @author Evan Geng
 */
public class QuadraSpeedController {

    /**
     * The first speed controller.
     */
    private final SpeedController a;

    /**
     * The second speed controller.
     */
    private final SpeedController b;

    /**
     * The third speed controller.
     */
    private final SpeedController c;

    /**
     * The fourth speed controller.
     */
    private final SpeedController d;

    /**
     * Creates a new controller set out of four controllers.
     * @param a The first controller.
     * @param b The second controller.
     * @param c The third controller.
     * @param d The fourth controller.
     */
    public QuadraSpeedController(SpeedController a, SpeedController b, SpeedController c, SpeedController d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Get the first controller.
     * @return The first controller.
     */
    public SpeedController getA() {
        return a;
    }

    /**
     * Get the second controller.
     * @return The second controller.
     */
    public SpeedController getB() {
        return b;
    }

    /**
     * Get the third controller.
     * @return The third controller.
     */
    public SpeedController getC() {
        return c;
    }

    /**
     * Get the fourth controller.
     * @return The fourth controller.
     */
    public SpeedController getD() {
        return d;
    }

    /**
     * Creates a new set of four {@link CANTalon}s.
     * @param a The first Talon.
     * @param b The second Talon.
     * @param c The third Talon.
     * @param d The fourth Talon.
     * @return The new controller set.
     */
    public static QuadraSpeedController ofCANTalons(int a, int b, int c, int d) {
        return new QuadraSpeedController(new CANTalon(a), new CANTalon(b), new CANTalon(c), new CANTalon(d));
    }

}
