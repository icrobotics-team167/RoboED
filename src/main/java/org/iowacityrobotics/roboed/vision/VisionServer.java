package org.iowacityrobotics.roboed.vision;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Provides vision stuff...!
 * @author Evan Geng
 */
public class VisionServer {

    /**
     * Default image parameters. // TODO Maybe make this configurable
     */
    private static final int RES_WIDTH = 320, RES_HEIGHT = 240;

    /**
     * A map of camera names to vision threads.
     */
    private static final Map<String, Thread> visionThreads = new HashMap<>();

    /**
     * Gets the image feed from a given camera.
     * @param type The camera type.
     * @param identifier The camera's identifier.
     * @param <T> The type of the identifier.
     * @return The camera feed.
     */
    public static <T> Supplier<Mat> getCamera(CameraType<T> type, T identifier) {
        CameraServer camServ = CameraServer.getInstance();
        if (type == CameraType.USB) {
            UsbCamera cam = camServ.startAutomaticCapture((Integer)identifier);
            cam.setResolution(RES_WIDTH, RES_HEIGHT);
            return new CachedImageFeed(camServ.getVideo(cam), 30L, RES_WIDTH, RES_HEIGHT, CvType.CV_8UC3);
        } else if (type == CameraType.AXIS) {
            AxisCamera cam = camServ.addAxisCamera((String)identifier);
            cam.setResolution(RES_WIDTH, RES_HEIGHT);
            return new CachedImageFeed(camServ.getVideo(cam), 30L, RES_WIDTH, RES_HEIGHT, CvType.CV_8UC3);
        }
        throw new IllegalArgumentException("Invalid camera type!");
    }

    /**
     * Registers an image source to put on the dashboard.
     * @param name The name of the image source.
     * @param provider The image provider.
     */
    public static void putImageSource(String name, Supplier<Mat> provider) {
        if (visionThreads.containsKey(name))
            visionThreads.get(name).interrupt();
        Thread thread = new Thread(() -> {
            CvSource out = CameraServer.getInstance().putVideo(name, RES_WIDTH, RES_HEIGHT);
            while (!Thread.interrupted()) {
                Mat frame = provider.get();
                out.putFrame(frame);
                frame.release();
            }
        });
        visionThreads.put(name, thread);
        thread.start();
    }

}
