package org.iowacityrobotics.roboed.api.data;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A pipeline segment that interpolates two other data streams.
 * @param <I1> The type of the first pipeline's data.
 * @param <I2> The type of the second pipeline's data.
 * @param <O> The output type.
 */
public class InterpolatingPipeline<I1, I2, O> implements IDataSource<O> {

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
    public <U, V> IDataSource<V> interpolate(IDataSource<U> other, BiFunction<O, U, V> interpolator) {
        return new InterpolatingPipeline<>(this, other, interpolator);
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
