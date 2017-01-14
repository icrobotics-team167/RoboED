package org.iowacityrobotics.roboed.impl.subsystem.impl;

import edu.wpi.first.wpilibj.RobotDrive;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.api.subsystem.provider.IQuadraPortSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;
import org.iowacityrobotics.roboed.impl.subsystem.FRCTerminalSubsystem;
import org.iowacityrobotics.roboed.util.robot.QuadraSpeedController;

/**
 * @author Evan Geng
 */
public class DualTreadSubsystem extends FRCTerminalSubsystem<DualTreadSubsystem.ControlDataFrame> {

    public static final ISubsystemType<ControlDataFrame, Void, Provider> TYPE = new FRCSubsystemType<>();
    public static final ISubsystemType<ControlDataFrame, Void, CustomProvider> TYPE_CUSTOM = new FRCSubsystemType<>();

    private final RobotDrive drive;

    protected DualTreadSubsystem(int id, RobotDrive drive) {
        super(TYPE, id);
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

        private final FRCSysRegistry registry;

        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }

        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(int port1, int port2, int port3, int port4) { // Front-left, front-right, back-left, back-right
            return new DualTreadSubsystem(registry.nextUnusedId(), new RobotDrive(port1, port2, port3, port4));
        }

    }

    public static class CustomProvider implements IGenericSubsystemProvider<ControlDataFrame, Void, QuadraSpeedController> {

        private final FRCSysRegistry registry;

        public CustomProvider(FRCSysRegistry registry) {
            this.registry = registry;
        }

        @Override
        public ISubsystem<ControlDataFrame, Void> getSubsystem(QuadraSpeedController controllers) {
            return registry.register(new DualTreadSubsystem(registry.nextUnusedId(), new RobotDrive(
                    controllers.getA(),
                    controllers.getB(),
                    controllers.getC(),
                    controllers.getD()
            )));
        }

    }
    
}
