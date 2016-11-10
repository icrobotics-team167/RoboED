package org.iowacityrobotics.roboed.impl.subsystem.impl;

import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IQuadraPortSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;
import org.iowacityrobotics.roboed.impl.subsystem.FRCTerminalSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.impl.MecanumSubsystem.ControlDataFrame;
import org.iowacityrobotics.roboed.util.math.Vector2;

import edu.wpi.first.wpilibj.RobotDrive;

/**
 * @author Evan Geng
 */
public class MecanumSubsystem extends FRCTerminalSubsystem<ControlDataFrame> {
    
    public static final ISubsystemType<ControlDataFrame, Void, Provider> TYPE = new ISubsystemType<ControlDataFrame, Void, Provider>() { };
    
    private final RobotDrive drive;

    protected MecanumSubsystem(int id, RobotDrive drive) {
        super(TYPE, id);
        this.drive = drive;
    }

    @Override
    protected void processData(ControlDataFrame data) {
        drive.mecanumDrive_Cartesian(data.direction.x(), data.direction.y(), data.pointRotation, 0D);
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

        private final FRCSysRegistry registry;
        
        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }
        
        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(int port1, int port2, int port3, int port4) { // Front-left, front-right, back-left, back-right
            return new MecanumSubsystem(registry.nextUnusedId(), new RobotDrive(port1, port2, port3, port4));
        }
        
    }
    
}
