package org.iowacityrobotics.roboed.data.mapper;

/**
 * Mapper that caches the returned value for a period of time.
 */
public class CachedMapper<I, O> implements Mapper<I, O> {

    /**
     * The backing mapper implementation.
     */
    private final Mapper<I, O> backing;

    /**
     * The time to cache for.
     */
    private final long cacheTime;

    /**
     * The cached return value.
     */
    private O cached = null;

    /**
     * The time of the last cache.
     */
    private long lastCache = -1L;

    /**
     * Creates a new caching mapper.
     * @param backing The backing mapper.
     * @param cacheTime The time to cache for, or -1 for indefinite caching.
     */
    public CachedMapper(Mapper<I, O> backing, long cacheTime) {
        this.backing = backing;
        this.cacheTime = cacheTime;
    }

    @Override
    public O apply(I data) {
        long currentTime = System.currentTimeMillis();
        if (lastCache == -1L || (cacheTime >= 0L && currentTime - lastCache > cacheTime)) {
            cached = backing.apply(data);
            lastCache = currentTime;
        }
        return cached;
    }

    @Override
    public void reset(boolean temp) {
        lastCache = -1L;
    }

}
