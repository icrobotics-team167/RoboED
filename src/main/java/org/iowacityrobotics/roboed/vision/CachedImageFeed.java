package org.iowacityrobotics.roboed.vision;

import edu.wpi.cscore.CvSink;
import org.opencv.core.Mat;

import java.util.function.Supplier;

/**
 * Image feed that caches camera data for a given amount of time.
 * @author Evan Geng
 */
public class CachedImageFeed implements Supplier<Mat> {

    /**
     * The backing image source.
     */
    private final CvSink backing;

    /**
     * The time to cache an image for.
     */
    private final long cacheTime;

    /**
     * The currently cached image.
     */
    private Mat cached;

    /**
     * The time of the last cache refresh.
     */
    private long lastCache;

    /**
     * Creates a cached image feed.
     * @param backing The backing image source.
     * @param cacheTime The time to cache an image for.
     */
    public CachedImageFeed(CvSink backing, long cacheTime, int width, int height, int type) {
        this.backing = backing;
        this.cacheTime = cacheTime;
        this.cached = new Mat(width, height, type);
        this.lastCache = 0L;
    }

    @Override
    public Mat get() {
        if (System.currentTimeMillis() - lastCache > cacheTime)
            backing.grabFrame(cached);
        return cached;
    }

}
