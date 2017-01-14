package org.iowacityrobotics.roboed.impl;

import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.api.IRobotProgram;
import org.iowacityrobotics.roboed.api.RobotMode;
import org.iowacityrobotics.roboed.api.operations.IOperationsManager;
import org.iowacityrobotics.roboed.api.subsystem.ISystemRegistry;
import org.iowacityrobotics.roboed.api.vision.ICameraServer;
import org.iowacityrobotics.roboed.impl.operations.FRCOpManager;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.iowacityrobotics.roboed.impl.vision.FRCCameraServer;

/** 
 * @author Evan Geng
 */
public class FRCRobot extends IterativeRobot implements IRobot {
    
    private final FRCSysRegistry sysReg;
    private final FRCOpManager opMan;
    private final FRCCameraServer camServ;
    private IRobotProgram prog;
    private RobotMode runMode;
    
    public FRCRobot() {
        this.sysReg = new FRCSysRegistry();
        this.opMan = new FRCOpManager(sysReg);
        this.camServ = new FRCCameraServer();
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
    public ICameraServer getCameraServer() {
        return camServ;
    }

    @Override
    public RobotMode getRunningMode() {
        return runMode;
    }
    
    private void setRunMode(RobotMode mode) {
        this.runMode = mode;
        sysReg.reset();
        opMan.modeChanged(mode);
    }

    private void tick() {
        // NO-OP
    }
    
    @Override
    public void robotInit() {
        prog = IRobotProgram.getImplementation();
        if (prog != null)
            prog.init(this);
        else
            System.out.println("[!] No robot program detected!"); // TODO Better logging implementation?
        opMan.initialize();
        setRunMode(RobotMode.DISABLED);
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
