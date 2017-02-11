package org.iowacityrobotics.roboed.util.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Represents a set of four speed controllers.
 * @author Evan Geng
 */
public class MotorTuple4 {

    /**
     * The front-left speed controller.
     */
    private final SpeedController fl;

    /**
     * The front-right speed controller.
     */
    private final SpeedController fr;

    /**
     * The rear-left speed controller.
     */
    private final SpeedController rl;

    /**
     * The rear-right speed controller.
     */
    private final SpeedController rr;

    /**
     * Creates a new controller set out of four controllers.
     * @param fl The front-left controller.
     * @param fr The front-right controller.
     * @param rl The rear-left controller.
     * @param rr The rear-right controller.
     */
    public MotorTuple4(SpeedController fl, SpeedController fr, SpeedController rl, SpeedController rr) {
        this.fl = fl;
        this.fr = fr;
        this.rl = rl;
        this.rr = rr;
    }

    /**
     * Get the front-left controller.
     * @return The front-left controller.
     */
    public SpeedController getFrontLeft() {
        return fl;
    }

    /**
     * Get the front-right controller.
     * @return The front-right controller.
     */
    public SpeedController getFrontRight() {
        return fr;
    }

    /**
     * Get the rear-left controller.
     * @return The rear-left controller.
     */
    public SpeedController getRearLeft() {
        return rl;
    }

    /**
     * Get the rear-right controller.
     * @return The rear-right controller.
     */
    public SpeedController getRearRight() {
        return rr;
    }

    /**
     * Creates a new set of four {@link CANTalon}s.
     * @param fl The front-left Talon.
     * @param fr The front-right Talon.
     * @param rl The rear-left Talon.
     * @param rr The rear-right Talon.
     * @return The new controller set.
     */
    public static MotorTuple4 ofCANTalons(int fl, int fr, int rl, int rr) {
        return new MotorTuple4(new CANTalon(fl), new CANTalon(fr), new CANTalon(rl), new CANTalon(rr));
    }

}
