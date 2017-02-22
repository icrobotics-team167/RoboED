package org.iowacityrobotics.roboed.data;

import org.iowacityrobotics.roboed.data.inter.Interpolator;
import org.iowacityrobotics.roboed.data.mapper.CachedMapper;
import org.iowacityrobotics.roboed.data.mapper.Mapper;
import org.iowacityrobotics.roboed.data.sink.Sink;
import org.iowacityrobotics.roboed.data.source.CachedSource;
import org.iowacityrobotics.roboed.data.source.Source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.*;

/**
 * Various lower-level data utilities.
 * @author Evan Geng
 */
public final class Data {

    /**
     * Collection of all stateful data objects.
     */
    private static final Collection<IStatefulData> statefuls = new ArrayList<>();

    /**
     * Creates a stateless source using the given supplier.
     * @param supplier The supplier.
     * @param <T> The supplied data type.
     * @return The new source.
     */
    public static <T> Source<T> source(Supplier<T> supplier) {
        return new StatelessSource<>(supplier);
    }

    /**
     * Creates a stateless mapper using the given function.
     * @param func The mapping function.
     * @param <I> The input data type.
     * @param <O> The output data type.
     * @return The new mapper.
     */
    public static <I, O> Mapper<I, O> mapper(Function<I, O> func) {
        return new StatelessMapper<>(func);
    }

    /**
     * Creates a stateless interpolator using the given function.
     * @param func The interpolating function.
     * @param <I1> Input data type 1.
     * @param <I2> Input data type 2.
     * @param <O> The output data type.
     * @return The new interpolator.
     */
    public static <I1, I2, O> Interpolator<I1, I2, O> inter(BiFunction<I1, I2, O> func) {
        return new StatelessInterpolator<>(func);
    }

    /**
     * Creates a stateless sink using the given processor.
     * @param consumer The processor.
     * @param <T> The type of data to consume.
     * @return The new sink.
     */
    public static <T> Sink<T> sink(Consumer<T> consumer) {
        return new StatelessSink<>(consumer);
    }

    /**
     * Creates a stateless sink using the given processor and default data.
     * @param consumer The processor.
     * @param defData The no-binding default data.
     * @param <T> The type of data to consume.
     * @return The new sink.
     */
    public static <T> Sink<T> sink(Consumer<T> consumer, T defData) {
        return new StatelessSink<>(consumer, defData);
    }

    /**
     * Creates a source that caches the value for a time.
     * @param backing The backing source.
     * @param cacheTime The time to cache for, or -1 for indefinite caching.
     * @param <T> The type of data to provide.
     * @return The new source.
     */
    public static <T> Source<T> cached(Source<T> backing, long cacheTime) {
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
    public static <I, O> Mapper<I, O> cached(Mapper<I, O> backing, long cacheTime) {
        return new CachedMapper<>(backing, cacheTime);
    }

    /**
     * Stores the current data pipeline state on a stack.
     */
    public static void pushState() {
        statefuls.forEach(IStatefulData::pushState);
    }

    /**
     * Pops the top element of the state stack and restores it.
     */
    public static void popState() {
        statefuls.forEach(IStatefulData::popState);
    }

    /**
     * Reset the state of all data objects.
     * @param temp Whether the reset was due to a temporary operation mode or not.
     */
    public static void reset(boolean temp) {
        statefuls.forEach(dObj -> dObj.reset(temp));
    }

    /**
     * Reset the state of all data objects that meet a criteria.
     * @param condition The condition for the data objects to satisfy.
     * @param temp Whether the reset was due to a temporary operation mode or not.
     */
    public static void resetIf(Predicate<IStatefulData> condition, boolean temp) {
        statefuls.stream()
                .filter(condition)
                .forEach(dObj -> dObj.reset(temp));
    }

    /**
     * Registers a stateful data object.
     * @param dObj The data object.
     */
    public static void registerStateful(IStatefulData dObj) {
        statefuls.add(dObj);
    }

    /**
     * A stateless implementation of {@link Source} that draws data from a {@link Supplier}.
     */
    public static final class StatelessSource<T> extends Source<T> {

        /**
         * The backing supplier.
         */
        private final Supplier<T> supplier;

        /**
         * Creates a source with the given supplier.
         * @param supplier The backing supplier.
         */
        public StatelessSource(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T get() {
            return supplier.get();
        }

    }

    /**
     * A stateless implementation of {@link Mapper} that uses a {@link Function} to process data.
     */
    public static final class StatelessMapper<I, O> extends Mapper<I, O> {

        /**
         * The backing function,
         */
        private final Function<I, O> func;

        /**
         * Creates a mapper with the given function.
         * @param func The backing function.
         */
        public StatelessMapper(Function<I, O> func) {
            this.func = func;
        }

        @Override
        public O apply(I data) {
            return func.apply(data);
        }

    }

    /**
     * A stateless implementation of {@link Interpolator} that uses a {@link BiFunction} to process data.
     */
    public static final class StatelessInterpolator<I1, I2, O> extends Interpolator<I1, I2, O> {

        /**
         * The backing function.
         */
        private final BiFunction<I1, I2, O> func;

        /**
         * Creates an interpolator with the given function.
         * @param func The backing function.
         */
        public StatelessInterpolator(BiFunction<I1, I2, O> func) {
            this.func = func;
        }

        @Override
        public O apply(I1 a, I2 b) {
            return func.apply(a, b);
        }

    }

    /**
     * A stateless implementation of {@link Sink} that uses a {@link Consumer} to process data.
     */
    public static final class StatelessSink<T> extends Sink<T> {

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
