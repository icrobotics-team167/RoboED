package org.iowacityrobotics.roboed.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The robot's main class.
 * TODO Document please
 * @author Evan Geng
 */
public class Robot extends IterativeRobot {

    static RobotMode runMode;

    public Robot() {
        runMode = RobotMode.UNINITIALIZED;
    }

    private void setRunMode(RobotMode mode) {
        runMode = mode;
        System.out.println("Switched to run mode: " + mode.toString());
        Flow.run(runMode.getOperation());
    }

    @Override
    public void robotInit() {
        IRobotProgram prog = IRobotProgram.getImplementation();
        if (prog != null)
            prog.init();
        else
            System.out.println("[!] No robot program detected!"); // TODO Better logging implementation?
    }

    @Override
    public void robotPeriodic() {
        // NO-OP
    }

    @Override
    public void disabledInit() {
        setRunMode(RobotMode.DISABLED);
    }

    @Override
    public void disabledPeriodic() {
        // NO-OP
    }

    @Override
    public void autonomousInit() {
        setRunMode(RobotMode.AUTO);
    }

    @Override
    public void autonomousPeriodic() {
        // NO-OP
    }

    @Override
    public void teleopInit() {
        setRunMode(RobotMode.TELEOP);
    }

    @Override
    public void teleopPeriodic() {
        // NO-OP
    }

    @Override
    public void testInit() {
        setRunMode(RobotMode.TEST);
    }

    @Override
    public void testPeriodic() {
        // NO-OP
    }

}
