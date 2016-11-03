package org.iowacityrobotics.roboed.impl;

import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.api.RobotMode;
import org.iowacityrobotics.roboed.api.operations.IOperationsManager;
import org.iowacityrobotics.roboed.api.subsystem.ISystemRegistry;
import org.iowacityrobotics.roboed.impl.operations.FRCOpManager;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;

import edu.wpi.first.wpilibj.IterativeRobot;

/** 
 * @author Evan Geng
 */
public class FRCRobot extends IterativeRobot implements IRobot {
    
    private final FRCSysRegistry sysReg;
    private final FRCOpManager opMan;
    private RobotMode runMode;
    
    public FRCRobot() {
        this.sysReg = new FRCSysRegistry();
        this.opMan = new FRCOpManager();
        this.runMode = RobotMode.UNINITIALIZED;
    }

    @Override
    public ISystemRegistry getSystemRegistry() {
        return sysReg;
    }

    @Override
    public IOperationsManager getOpManager() {
        return opMan;
    }

    @Override
    public RobotMode getRunningMode() {
        return runMode;
    }
    
    private void setRunMode(RobotMode mode) {
        this.runMode = mode;
        opMan.modeChanged(mode);
    }
    
    private void tick() {
        sysReg.tick();
        opMan.tick();
    }
    
    @Override
    public void robotInit() {
        setRunMode(RobotMode.DISABLED);
    }

    @Override
    public void disabledInit() {
        setRunMode(RobotMode.DISABLED);
    }

    @Override
    public void disabledPeriodic() {
        tick();
    }
    
    @Override
    public void autonomousInit() {
        setRunMode(RobotMode.AUTO);
    }
    
    @Override
    public void autonomousPeriodic() {
        tick();
    }

    @Override
    public void teleopInit() {
        setRunMode(RobotMode.TELEOP);
    }
    
    @Override
    public void teleopPeriodic() {
        tick();
    }

    @Override
    public void testInit() {
        setRunMode(RobotMode.TEST);
    }

    @Override
    public void testPeriodic() {
        tick();
    }

}
