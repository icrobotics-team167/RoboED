package org.iowacityrobotics.roboed.data.source;

/**
 * Source that caches the returned value for a period of time.
 */
public class CachedSource<T> extends Source<T> {

    /**
     * The backing source.
     */
    private final Source<T> backing;

    /**
     * The time to cache for.
     */
    private final long cacheTime;

    /**
     * The cached return value.
     */
    private T cached = null;

    /**
     * The time of the last cache.
     */
    private long lastCache = -1L;

    /**
     * Creates a new caching source.
     * @param backing The backing source.
     * @param cacheTime The time to cache for, or -1 for indefinite caching.
     */
    public CachedSource(Source<T> backing, long cacheTime) {
        this.backing = backing;
        this.cacheTime = cacheTime;
    }

    @Override
    public T get() {
        long currentTime = System.currentTimeMillis();
        if (lastCache == -1L || (cacheTime >= 0L && currentTime - lastCache > cacheTime)) {
            cached = backing.get();
            lastCache = currentTime;
        }
        return cached;
    }

    @Override
    public void reset(boolean temp) {
        lastCache = -1L;
    }
    
}
