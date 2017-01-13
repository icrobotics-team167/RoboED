package org.iowacityrobotics.roboed.impl.vision;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import org.iowacityrobotics.roboed.api.vision.CameraType;
import org.iowacityrobotics.roboed.api.vision.ICameraServer;
import org.iowacityrobotics.roboed.api.vision.IImageProvider;

/**
 * @author Evan Geng
 */
public class FRCCameraServer implements ICameraServer {

    @Override
    public <T> IImageProvider getCamera(CameraType<T> type, T identifier) {
        CameraServer camServ = CameraServer.getInstance();
        if (type == CameraType.USB) {
            UsbCamera cam = camServ.startAutomaticCapture((int)identifier);
            return new CachedCameraFeed(camServ.getVideo(cam), 30L);
        } else if (type == CameraType.AXIS) {
            AxisCamera cam = camServ.addAxisCamera((String)identifier);
            return new CachedCameraFeed(camServ.getVideo(cam), 30L);
        }
        throw new IllegalArgumentException("Invalid camera type!");
    }

    @Override
    public void putImageSource(IImageProvider provider) {
        // TODO Implement
    }

}
