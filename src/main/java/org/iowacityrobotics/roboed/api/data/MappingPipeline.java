package org.iowacityrobotics.roboed.api.data;

import java.util.function.Function;

/**
 * Data pipeline element that maps one datatype to another via an intermediate processing function.
 * @author Evan Geng
 */
public class MappingPipeline<I, O> implements IDataSource<O> {
    
    /**
     * The backing data source.
     */
    private final IDataSource<I> input;
    
    /**
     * The mapping function.
     */
    private final Function<I, O> mapper;
    
    /**
     * Creates a new mapping pipeline segment using the given input stream and mapping function.
     * @param input The input stream.
     * @param mapper The mapping function.
     */
    public MappingPipeline(IDataSource<I> input, Function<I, O> mapper) {
        this.input = input;
        this.mapper = mapper;
    }
    
    @Override
    public <V> IDataSource<V> map(Function<O, V> mapper) {
        return new MappingPipeline<>(this, mapper);
    }

    @Override
    public O request() {
        I val = input.request();
        return val == null ? null : mapper.apply(val);
    }
    
}