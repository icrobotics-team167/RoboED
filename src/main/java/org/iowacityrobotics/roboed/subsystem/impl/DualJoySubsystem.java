package org.iowacityrobotics.roboed.subsystem.impl;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISinglePortSubsystemProvider;
import org.iowacityrobotics.roboed.subsystem.FRCSourceSubsystem;
import org.iowacityrobotics.roboed.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.util.collection.Pair;
import org.iowacityrobotics.roboed.util.math.Vector2;

/**
 * @author Evan Geng
 */
public class DualJoySubsystem extends FRCSourceSubsystem<Pair<Vector2, Vector2>> {
    
    public static final ISubsystemType<Void, Pair<Vector2, Vector2>, ISinglePortSubsystemProvider<Void, Pair<Vector2, Vector2>>> TYPE = new FRCSubsystemType<>();
    
    private final IDataSource<Pair<Vector2, Vector2>> upstream;

    protected DualJoySubsystem(XboxController cont) {
        super(TYPE);
        upstream = Data.provider(() -> new Pair<>(
                new Vector2(cont.getX(GenericHID.Hand.kLeft), cont.getY(GenericHID.Hand.kLeft)),
                new Vector2(cont.getX(GenericHID.Hand.kRight), cont.getY(GenericHID.Hand.kRight))
        ));
    }

    @Override
    public IDataSource<Pair<Vector2, Vector2>> output() {
        return upstream;
    }
    
    public static class Provider implements ISinglePortSubsystemProvider<Void, Pair<Vector2, Vector2>> {
        
        @Override
        public ISubsystem<Void, Pair<Vector2, Vector2>> getSubsystem(int port) {
            return new DualJoySubsystem(new XboxController(port));
        }
        
    }

}
