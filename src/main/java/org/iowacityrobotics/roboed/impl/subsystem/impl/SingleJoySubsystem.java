package org.iowacityrobotics.roboed.impl.subsystem.impl;

import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISinglePortSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSourceSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;
import org.iowacityrobotics.roboed.util.math.Vector3;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Evan Geng
 */
public class SingleJoySubsystem extends FRCSourceSubsystem<Vector3> {
    
    public static final ISubsystemType<Void, Vector3, ISinglePortSubsystemProvider<Void, Vector3>> TYPE = new FRCSubsystemType<>();
    
    private final IDataSource<Vector3> upstream;

    protected SingleJoySubsystem(Joystick joy) {
        super(TYPE);
        upstream = Data.provider(() -> new Vector3(joy.getAxis(Joystick.AxisType.kX), joy.getAxis(Joystick.AxisType.kY), joy.getAxis(Joystick.AxisType.kTwist)));
    }

    @Override
    public IDataSource<Vector3> output() {
        return upstream;
    }
    
    public static class Provider implements ISinglePortSubsystemProvider<Void, Vector3> {
        
        @Override
        public ISubsystem<Void, Vector3> getSubsystem(int port) {
            return new SingleJoySubsystem(new Joystick(port));
        }
        
    }

}
