package org.iowacityrobotics.roboed.impl.subsystem.impl;

import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISinglePortSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSourceSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;
import org.iowacityrobotics.roboed.util.math.Vector3;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Evan Geng
 */
public class SingleJoySubsystem extends FRCSourceSubsystem<Vector3> {
    
    public static final ISubsystemType<Void, Vector3, Provider> TYPE = new ISubsystemType<Void, Vector3, Provider>() { };
    
    private final IDataSource<Vector3> upstream;

    protected SingleJoySubsystem(int id, Joystick joy) {
        super(TYPE, id);
        upstream = Data.provider(() -> new Vector3(joy.getAxis(Joystick.AxisType.kX), joy.getAxis(Joystick.AxisType.kY), joy.getAxis(Joystick.AxisType.kZ)));
    }

    @Override
    public IDataSource<Vector3> output() {
        return upstream;
    }
    
    public static class Provider implements ISinglePortSubsystemProvider<Void, Vector3> {

        private final FRCSysRegistry registry;
        
        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }
        
        @Override
        public ISubsystem<Void, Vector3> getSubsystem(int port) {
            return new SingleJoySubsystem(registry.nextUnusedId(), new Joystick(port));
        }
        
    }

}
