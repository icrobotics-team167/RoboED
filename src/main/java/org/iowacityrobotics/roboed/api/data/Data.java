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
     * Creates a pipeline segment that interpolates data from two pipelines.
     * @param src1 The first pipeline.
     * @param src2 The second pipeline.
     * @param mapper The data interpolation function.
     * @return The pipeline with the new interpolating segment.
     */
    public static <I1, I2, O> IDataSource<O> interpolate(IDataSource<I1> src1, IDataSource<I2> src2, BiFunction<I1, I2, O> mapper) {
        return new InterpolatingPipeline<>(src1, src2, mapper);
    }
    
    /**
     * The implementation of {@link Data#interpolate(IDataSource, IDataSource, BiFunction) interpolate()}.
     */
    private static class InterpolatingPipeline<I1, I2, O> implements IDataSource<O> {

        private final IDataSource<I1> src1;
        private final IDataSource<I2> src2;
        private final BiFunction<I1, I2, O> mapper;
        
        InterpolatingPipeline(IDataSource<I1> src1, IDataSource<I2> src2, BiFunction<I1, I2, O> mapper) {
            this.src1 = src1;
            this.src2 = src2;
            this.mapper = mapper;
        }
        
        @Override
        public <V> IDataSource<V> map(Function<O, V> mapper) {
            return new MappingPipeline<>(this, mapper);
        }

        @Override
        public O request() {
            I1 val1 = src1.request();
            if (val1 != null) {
                I2 val2 = src2.request();
                if (val2 != null)
                    return mapper.apply(val1, val2);
            }
            return null;
        }
        
    }

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
        public T request() {
            return provider.get();
        }
    }
    
}
