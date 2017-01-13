package org.iowacityrobotics.roboed.impl.vision;

import edu.wpi.cscore.CvSink;
import org.iowacityrobotics.roboed.api.vision.IImageProvider;
import org.opencv.core.Mat;

/**
 * @author Evan Geng
 */
public class CachedCameraFeed implements IImageProvider {

    private final CvSink backing;
    private final long cacheTime;
    private Mat cached;
    private long lastCache;

    public CachedCameraFeed(CvSink backing, long cacheTime) {
        this.backing = backing;
        this.cacheTime = cacheTime;
        this.cached = new Mat();
        this.lastCache = 0L;
    }

    @Override
    public Mat getImage() {
        if (System.currentTimeMillis() - lastCache > cacheTime)
            backing.grabFrame(cached);
        return cached;
    }

}
