package org.iowacityrobotics.roboed.impl.subsystem.impl;

import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.DataUnavailableException;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;
import org.iowacityrobotics.roboed.impl.subsystem.impl.VisionServerSubsystem.VisionFrame;

/**
 * @author Evan Geng
 */
public class VisionServerSubsystem extends FRCSubsystem<String, VisionFrame> {

    public static final ISubsystemType<String, VisionFrame, Provider> TYPE = new ISubsystemType<String, VisionFrame, Provider>() { };
    
    private final String host;
    private final IDataSource<VisionFrame> output;
    private IDataSource<String> input;
    
    protected VisionServerSubsystem(int id, String host) {
        super(TYPE, id);
        this.host = host;
        this.output = Data.provider(() -> {
            if (input == null)
                throw new DataUnavailableException("No routine ID provided!");
            // TODO Request vision data
            throw new UnsupportedOperationException("No implementation!");
        });
    }
    
    @Override
    public void bind(IDataSource<String> input) {
        this.input = input;
    }

    @Override
    public IDataSource<VisionFrame> output() {
        return output;
    }

    public static class VisionFrame {
        
        public final String routineId;
        public final byte[] data;
        
        public VisionFrame(String routineId, byte[] data) {
            this.routineId = routineId;
            this.data = data;
        }
        
    }
    
    public static class Provider implements IGenericSubsystemProvider<String, VisionFrame, String> {

        private final FRCSysRegistry registry;
        
        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }

        @Override
        public ISubsystem<String, VisionFrame> getSubsystem(String host) {
            return new VisionServerSubsystem(registry.nextUnusedId(), host);
        }
        
    }
    
}
