package org.iowacityrobotics.roboed.api.data;

import java.util.function.BiFunction;
import java.util.function.Function;

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
     * @return The pipelines with the new interpolating segment.
     */
    public static <I1, I2, O> IDataSource<O> interpolate(IDataSource<I1> src1, IDataSource<I2> src2, BiFunction<I1, I2, O> mapper) {
        return new InterpolatingPipeline<>(src1, src2, mapper);
    }
    
    /**
     * The implementation of {@link Data#interpolate(IDataSource, IDataSource, BiFunction) interpolate()}.
     * @author Evan Geng
     */
    static class InterpolatingPipeline<I1, I2, O> implements IDataSource<O> {

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
    
}
