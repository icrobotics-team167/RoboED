package org.iowacityrobotics.roboed.data;

import org.iowacityrobotics.roboed.data.mapper.CachedMapper;
import org.iowacityrobotics.roboed.data.mapper.IMapper;
import org.iowacityrobotics.roboed.data.sink.AbstractSink;
import org.iowacityrobotics.roboed.data.sink.ISink;
import org.iowacityrobotics.roboed.data.source.CachedSource;
import org.iowacityrobotics.roboed.data.source.ISource;

import java.util.function.Consumer;

/**
 * Various lower-level data utilities.
 * @author Evan Geng
 */
public final class Data {

    /**
     * Creates a stateless sink using the given processor.
     * @param consumer The processor.
     * @param <T> The type of data to consume.
     * @return The new sink.
     */
    public static <T> ISink<T> sink(Consumer<T> consumer) {
        return new StatelessSink<>(consumer);
    }

    /**
     * Creates a stateless sink using the given processor and default data.
     * @param consumer The processor.
     * @param defData The no-binding default data.
     * @param <T> The type of data to consume.
     * @return The new sink.
     */
    public static <T> ISink<T> sink(Consumer<T> consumer, T defData) {
        return new StatelessSink<>(consumer, defData);
    }

    /**
     * Creates a source that caches the value for a time.
     * @param backing The backing source.
     * @param cacheTime The time to cache for, or -1 for indefinite caching.
     * @param <T> The type of data to provide.
     * @return The new source.
     */
    public static <T> ISource<T> cached(ISource<T> backing, long cacheTime) {
        return new CachedSource<>(backing, cacheTime);
    }

    /**
     * Creates a mapper that caches the value for a time.
     * @param backing The backing mapper.
     * @param cacheTime The time to cache for, or -1 for indefinite caching.
     * @param <I> The input data type.
     * @param <O> The output data type.
     * @return The new mapper.
     */
    public static <I, O> IMapper<I, O> cached(IMapper<I, O> backing, long cacheTime) {
        return new CachedMapper<>(backing, cacheTime);
    }

    /**
     * Stores the current binding state on a stack.
     */
    public static void pushState() { // TODO Maybe some kind of annotation-based thingy for this
        AbstractSink.pushStateAll(); // TODO Make it affect other data stuff too
    }

    /**
     * Pops the top element of the state stack and restores it.
     */
    public static void popState() {
        AbstractSink.popStateAll(); // TODO Make it affect other data stuff too
    }

    /**
     * Reset the state of all sinks.
     * @param temp Whether the reset was due to a temporary operation mode or not.
     */
    public static void reset(boolean temp) {
        AbstractSink.resetAll(temp); // TODO Make it affect other data stuff too
    }

    /**
     * A stateless implementation of {@link ISink} that uses a {@link Consumer} to process data.
     * @author Evan Geng
     */
    private static final class StatelessSink<T> extends AbstractSink<T> {

        /**
         * The backing processor.
         */
        private final Consumer<T> consumer;

        /**
         * No-binding default value.
         */
        private final T defData;

        /**
         * Creates a sink with the given processor.
         * @param consumer The backing processor.
         */
        public StatelessSink(Consumer<T> consumer) {
            this(consumer, null);
        }

        /**
         * Creates a sink with the given processor and default data.
         * @param consumer The backing processor.
         * @param defData The no-binding default value.
         */
        public StatelessSink(Consumer<T> consumer, T defData) {
            this.consumer = consumer;
            this.defData = defData;
        }

        @Override
        protected void process(T data) {
            consumer.accept(data);
        }

        @Override
        protected void noData() {
            consumer.accept(defData);
        }

    }

}
