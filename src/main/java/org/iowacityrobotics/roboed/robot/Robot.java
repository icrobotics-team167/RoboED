package org.iowacityrobotics.roboed.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.iowacityrobotics.roboed.data.sink.AbstractSink;

/**
 * The robot's main class.
 * TODO Document please
 * @author Evan Geng
 */
public class Robot extends IterativeRobot {

    private IRobotProgram prog;
    private RobotMode runMode;
    
    public Robot() {
        this.runMode = RobotMode.UNINITIALIZED;
    }

    public RobotMode getRunningMode() {
        return runMode;
    }

    private void setRunMode(RobotMode mode) {
        this.runMode = mode;
    }

    private void tick() {
        AbstractSink.tickAll();
    }

    @Override
    public void robotInit() {
        prog = IRobotProgram.getImplementation();
        if (prog != null)
            prog.init(this);
        else
            System.out.println("[!] No robot program detected!"); // TODO Better logging implementation?
        setRunMode(RobotMode.DISABLED);
    }

    @Override
    public void robotPeriodic() {
        tick();
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
