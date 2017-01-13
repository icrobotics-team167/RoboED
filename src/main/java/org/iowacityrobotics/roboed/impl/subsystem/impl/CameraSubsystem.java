package org.iowacityrobotics.roboed.impl.subsystem.impl;

import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.api.vision.ICamera;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSourceSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;
import org.opencv.core.Mat;

/**
 * @author Evan Geng
 */
public class CameraSubsystem extends FRCSourceSubsystem<Mat> { // TODO Fix this class!!!!!!

    public static final ISubsystemType<Void, Mat, Provider> TYPE = new FRCSubsystemType<>();
    
    private final ICamera camera;
    private final IDataSource<Mat> output;
    
    public CameraSubsystem(int id, ICamera camera) {
        super(TYPE, id);
        this.camera = camera;
        this.output = Data.provider(camera::capture);
    }
    
    @Override
    public IDataSource<Mat> output() {
        return output;
    }
    
    public static class Provider implements IGenericSubsystemProvider<Void, Mat, ICamera> {
        
        private final FRCSysRegistry registry;
        
        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }

        @Override
        public ISubsystem<Void, Mat> getSubsystem(ICamera camera) {
            return new CameraSubsystem(registry.nextUnusedId(), camera);
        }
        
    }
    
}
