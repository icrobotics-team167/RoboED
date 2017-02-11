package org.iowacityrobotics.roboed.subsystem.impl;

import edu.wpi.first.wpilibj.RobotDrive;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.api.subsystem.provider.IQuadraPortSubsystemProvider;
import org.iowacityrobotics.roboed.util.robot.MotorTuple4;

/**
 * @author Evan Geng
 */
public class DualTreadSubsystem extends FRCTerminalSubsystem<DualTreadSubsystem.ControlDataFrame> {

    public static final ISubsystemType<ControlDataFrame, Void, IQuadraPortSubsystemProvider<ControlDataFrame, Void>> TYPE = new FRCSubsystemType<>();
    public static final ISubsystemType<ControlDataFrame, Void, IGenericSubsystemProvider<ControlDataFrame, Void, MotorTuple4>> TYPE_CUSTOM = new FRCSubsystemType<>();

    private final RobotDrive drive;

    protected DualTreadSubsystem(RobotDrive drive) {
        super(TYPE);
        this.drive = drive;
    }

    @Override
    protected void processData(ControlDataFrame data) {
        drive.tankDrive(data.leftTread, data.rightTread);
    }

    public static class ControlDataFrame {

        public final double leftTread, rightTread;

        public ControlDataFrame(double leftTread, double rightTread) {
            this.leftTread = leftTread;
            this.rightTread = rightTread;
        }

    }

    public static class Provider implements IQuadraPortSubsystemProvider<ControlDataFrame, Void> {

        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(int port1, int port2, int port3, int port4) { // Front-left, front-right, back-left, back-right
            return new DualTreadSubsystem(new RobotDrive(port1, port2, port3, port4));
        }

    }

    public static class CustomProvider implements IGenericSubsystemProvider<ControlDataFrame, Void, MotorTuple4> {

        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(MotorTuple4 controllers) {
            return new DualTreadSubsystem(new RobotDrive(
                    controllers.getFrontLeft(),
                    controllers.getFrontRight(),
                    controllers.getRearLeft(),
                    controllers.getRearRight()
            ));
        }

    }
    
}
