package org.iowacityrobotics.roboed.api.data;

import java.util.function.Function;

public class MappingPipeline<I, O> implements IDataSource<O> {
    
    private final IDataSource<I> input;
    private final Function<I, O> mapper;
    
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