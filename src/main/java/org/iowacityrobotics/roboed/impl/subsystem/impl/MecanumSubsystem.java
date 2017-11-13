package org.iowacityrobotics.roboed.impl.subsystem.impl;

import edu.wpi.first.wpilibj.RobotDrive;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.api.subsystem.provider.IQuadraPortSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.impl.subsystem.FRCTerminalSubsystem;
import org.iowacityrobotics.roboed.util.math.Vector2;
import org.iowacityrobotics.roboed.util.robot.QuadraSpeedController;

/**
 * @author Evan Geng
 */
public class MecanumSubsystem extends FRCTerminalSubsystem<MecanumSubsystem.ControlDataFrame> {
    
    public static final ISubsystemType<ControlDataFrame, Void, IQuadraPortSubsystemProvider<ControlDataFrame, Void>> TYPE = new FRCSubsystemType<>();
    public static final ISubsystemType<ControlDataFrame, Void, IGenericSubsystemProvider<ControlDataFrame, Void, QuadraSpeedController>> TYPE_CUSTOM = new FRCSubsystemType<>();
    
    private final RobotDrive drive;

    protected MecanumSubsystem(RobotDrive drive) {
        super(TYPE);
        this.drive = drive;
    }

    @Override
    protected void processData(ControlDataFrame data) {
        drive.mecanumDrive_Cartesian(data.direction.x() * -1, data.direction.y(), data.pointRotation, 0D);
    }
    
    public static class ControlDataFrame {
        
        public final Vector2 direction;
        public final double pointRotation;
        
        public ControlDataFrame(Vector2 direction, double pointRotation) {
            this.direction = direction;
            this.pointRotation = pointRotation;
        }
        
    }
    
    public static class Provider implements IQuadraPortSubsystemProvider<ControlDataFrame, Void> {
        
        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(int port1, int port2, int port3, int port4) { // Front-left, front-right, back-left, back-right
            return new MecanumSubsystem(new RobotDrive(port1, port2, port3, port4));
        }
        
    }

    public static class CustomProvider implements IGenericSubsystemProvider<ControlDataFrame, Void, QuadraSpeedController> {

        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(QuadraSpeedController controllers) {
            return new MecanumSubsystem(new RobotDrive(
                    controllers.getA(),
                    controllers.getB(),
                    controllers.getC(),
                    controllers.getD()
            ));
        }

    }
    
}
