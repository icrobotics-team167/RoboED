package org.iowacityrobotics.roboed.impl.vision;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import org.iowacityrobotics.roboed.api.vision.CameraType;
import org.iowacityrobotics.roboed.api.vision.ICameraServer;
import org.iowacityrobotics.roboed.api.vision.IImageProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Evan Geng
 */
public class FRCCameraServer implements ICameraServer {

    private static final int RES_WIDTH = 640, RES_HEIGHT = 480;

    private final Map<String, Thread> visionThreads = new HashMap<>();

    @Override
    public <T> IImageProvider getCamera(CameraType<T> type, T identifier) {
        CameraServer camServ = CameraServer.getInstance();
        if (type == CameraType.USB) {
            UsbCamera cam = camServ.startAutomaticCapture((Integer)identifier);
            cam.setResolution(RES_WIDTH, RES_HEIGHT);
            return new CachedCameraFeed(camServ.getVideo(cam), 30L);
        } else if (type == CameraType.AXIS) {
            AxisCamera cam = camServ.addAxisCamera((String)identifier);
            cam.setResolution(RES_WIDTH, RES_HEIGHT);
            return new CachedCameraFeed(camServ.getVideo(cam), 30L);
        }
        throw new IllegalArgumentException("Invalid camera type!");
    }

    @Override
    public void putImageSource(String name, IImageProvider provider) {
        if (visionThreads.containsKey(name))
            visionThreads.get(name).interrupt();
        Thread thread = new Thread(() -> {
            CvSource out = CameraServer.getInstance().putVideo(name, RES_WIDTH, RES_HEIGHT);
            while (true) out.putFrame(provider.getImage());
        });
        visionThreads.put(name, thread);
        thread.start();
    }

}
