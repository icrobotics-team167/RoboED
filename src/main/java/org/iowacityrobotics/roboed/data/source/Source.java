package org.iowacityrobotics.roboed.data.source;

import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.IStatefulData;
import org.iowacityrobotics.roboed.data.inter.Interpolator;
import org.iowacityrobotics.roboed.data.inter.InterpolatingSource;
import org.iowacityrobotics.roboed.data.mapper.Mapper;
import org.iowacityrobotics.roboed.data.mapper.MappingSource;

/**
 * Provides data.
 * @author Evan Geng
 */
public abstract class Source<T> implements IStatefulData {

    /**
     * Constructs a source.
     */
    public Source() {
        Data.registerStateful(this);
    }

    /**
     * Retrieve data from this source.
     * @return The data.
     */
    public abstract T get();

    /**
     * Creates a mapping pipeline segment.
     * @param mapper The mapping function.
     * @param <O> The output data type.
     * @return The newly-created pipeline segment.
     */
    public <O> Source<O> map(Mapper<T, O> mapper) {
        return new MappingSource<>(this, mapper);
    }

    /**
     * Creates an interpolating pipeline segment.
     * @param src The other data source.
     * @param inter The interpolating function.
     * @param <V> The other data source's type.
     * @param <O> The output data type.
     * @return The newly-created pipeline segment.
     */
    public <V, O> Source<O> inter(Source<V> src, Interpolator<T, V, O> inter) {
        return new InterpolatingSource<>(this, src, inter);
    }

}
