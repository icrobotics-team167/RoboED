package org.iowacityrobotics.roboed.api.data;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Utility class for working with data streams.
 * @author Evan Geng
 */
public class Data {

    /**
     * Creates a pipeline that's fed from a {@link Supplier}.
     * @param provider The supplier feeding this pipeline.
     * @param <T> The type of data being passed.
     * @return The new pipeline.
     */
    public static <T> IDataSource<T> provider(Supplier<T> provider) {
        return new ProviderPipeline<>(provider);
    }

    /**
     * Creates a pipeline that always provides a constant value.
     * @param value The value to be provided.
     * @param <T> The type of data being passed.
     * @return The new pipeline.
     */
    public static <T> IDataSource<T> constant(T value) {
        return provider(() -> value);
    }

    /**
     * The implementation of {@link Data#provider(Supplier) provider()}.
     */
    private static class ProviderPipeline<T> implements IDataSource<T> {

        private final Supplier<T> provider;

        ProviderPipeline(Supplier<T> provider) {
            this.provider = provider;
        }

        @Override
        public <V> IDataSource<V> map(Function<T, V> mapper) {
            return new MappingPipeline<>(this, mapper);
        }

        @Override
        public <U, V> IDataSource<V> interpolate(IDataSource<U> other, BiFunction<T, U, V> interpolator) {
            return new InterpolatingPipeline<>(this, other, interpolator);
        }

        @Override
        public T request() {
            return provider.get();
        }

    }
    
}
