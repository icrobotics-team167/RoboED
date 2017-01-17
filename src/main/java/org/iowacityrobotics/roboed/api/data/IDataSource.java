package org.iowacityrobotics.roboed.api.data;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Represents a pipeline to facilitate the transfer of data.
 * @author Evan Geng
 */
public interface IDataSource<T> {

    /**
     * Create a mapping pipeline segment that transforms data.
     * @param mapper The mapping function.
     * @return The pipeline with the new segment attached.
     */
    <V> IDataSource<V> map(Function<T, V> mapper);

    /**
     * Create an interpolating pipeline segment that interpolates two data streams.
     * @param other The other data pipeline.
     * @param interpolator The interpolation function,
     * @return The new interpolating pipeline.
     */
    <U, V> IDataSource<V> interpolate(IDataSource<U> other, BiFunction<T, U, V> interpolator);
    
    /**
     * Requests data from this pipeline.
     * @return The data received, if any.
     * @throws DataUnavailableException If no data is available.
     */
    T request();
    
}
