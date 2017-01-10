package org.iowacityrobotics.roboed.impl.subsystem.impl;

import edu.wpi.first.wpilibj.vision.USBCamera;
import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSourceSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;

import java.nio.ByteBuffer;

/**
 * @author Evan Geng
 */
public class USBCameraSubsystem extends FRCSourceSubsystem<byte[]> {

    public static final ISubsystemType<Void, byte[], Provider> TYPE = new FRCSubsystemType<>();
    
    private final USBCamera cam;
    private final IDataSource<byte[]> output;
    
    public USBCameraSubsystem(int id, String camName) {
        super(TYPE, id);
        this.cam = new USBCamera(camName);
        this.output = Data.provider(() -> {
            ByteBuffer buf = ByteBuffer.allocate(); // TODO Fix pls
            cam.getImageData(buf);
            return buf.array();
        });
    }
    
    @Override
    public IDataSource<byte[]> output() {
        return output;
    }
    
    public static class Provider implements IGenericSubsystemProvider<Void,byte[],String> {
        
        private final FRCSysRegistry registry;
        
        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }

        @Override
        public ISubsystem<Void, byte[]> getSubsystem(String camName) {
            return new USBCameraSubsystem(registry.nextUnusedId(), camName);
        }
        
    }
    
}
