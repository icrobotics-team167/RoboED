package org.iowacityrobotics.roboed.impl.subsystem.impl;

import edu.wpi.cscore.AxisCamera;
import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.DataUnavailableException;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSourceSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;


/**
 * @author Evan Geng
 */
public class AxisCameraSubsystem extends FRCSourceSubsystem<HSLImage> { // TODO Fix this class!!!!!!
    
    public static final ISubsystemType<Void, HSLImage, Provider> TYPE = new FRCSubsystemType<>();
    
    private final IDataSource<HSLImage> upstream;

    protected AxisCameraSubsystem(int id, AxisCamera cam) {
        super(TYPE, id);
        upstream = Data.provider(() -> {
            try {

                return cam.getImage();
            } catch (NIVisionException e) {
                throw new DataUnavailableException(e);
            }
        });
    }

    @Override
    public IDataSource<HSLImage> output() {
        return upstream;
    }
    
    public static class Provider implements IGenericSubsystemProvider<Void, HSLImage, String> {

        private final FRCSysRegistry registry;
        
        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }
        
        @Override
        public ISubsystem<Void, HSLImage> getSubsystem(String address) {
            return new AxisCameraSubsystem(registry.nextUnusedId(), new AxisCamera(address));
        }
        
    }

}
