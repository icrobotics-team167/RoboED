package org.iowacityrobotics.roboed.api.data;

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
     * Requests data from this pipeline.
     * @return The data received, if any.
     */
    T request();
    
}
