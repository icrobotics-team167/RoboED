package org.iowacityrobotics.roboed.subsystem.impl;

import edu.wpi.first.wpilibj.RobotDrive;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.api.subsystem.provider.IQuadraPortSubsystemProvider;
import org.iowacityrobotics.roboed.util.math.Vector2;
import org.iowacityrobotics.roboed.util.robot.MotorTuple4;

/**
 * @author Evan Geng
 */
public class MecanumSubsystem extends FRCTerminalSubsystem<MecanumSubsystem.ControlDataFrame> {
    
    public static final ISubsystemType<ControlDataFrame, Void, IQuadraPortSubsystemProvider<ControlDataFrame, Void>> TYPE = new FRCSubsystemType<>();
    public static final ISubsystemType<ControlDataFrame, Void, IGenericSubsystemProvider<ControlDataFrame, Void, MotorTuple4>> TYPE_CUSTOM = new FRCSubsystemType<>();
    
    private final RobotDrive drive;

    protected MecanumSubsystem(RobotDrive drive) {
        super(TYPE);
        this.drive = drive;
    }

    @Override
    protected void processData(ControlDataFrame data) {
        drive.mecanumDrive_Cartesian(data.direction.x() * -1, data.direction.y(), data.pointRotation, data.gyro);
    }
    
    public static class ControlDataFrame {
        
        public final Vector2 direction;
        public final double pointRotation;
        public final double gyro;

        public ControlDataFrame(Vector2 direction, double pointRotation) {
            this(direction, pointRotation, 0D);
        }
        
        public ControlDataFrame(Vector2 direction, double pointRotation, double gyro) {
            this.direction = direction;
            this.pointRotation = pointRotation;
            this.gyro = gyro;
        }
        
    }
    
    public static class Provider implements IQuadraPortSubsystemProvider<ControlDataFrame, Void> {
        
        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(int port1, int port2, int port3, int port4) { // Front-left, front-right, back-left, back-right
            return new MecanumSubsystem(new RobotDrive(port1, port2, port3, port4));
        }
        
    }

    public static class CustomProvider implements IGenericSubsystemProvider<ControlDataFrame, Void, MotorTuple4> {

        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(MotorTuple4 controllers) {
            return new MecanumSubsystem(new RobotDrive(
                    controllers.getFrontLeft(),
                    controllers.getFrontRight(),
                    controllers.getRearLeft(),
                    controllers.getRearRight()
            ));
        }

    }
    
}
