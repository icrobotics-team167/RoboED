package org.iowacityrobotics.roboed.data.mapper;

import org.iowacityrobotics.roboed.data.source.Source;

/**
 * The implementation of {@link Source#map}.
 * @author Evan Geng
 */
public final class MappingSource<I, O> implements Source<O> {

    /**
     * The backing data source.
     */
    private final Source<I> src;

    /**
     * The mapping function.
     */
    private final Mapper<I, O> mapper;

    /**
     * Creates a new mapping pipeline element.
     * @param src The backing data source.
     * @param mapper The mapping function.
     */
    public MappingSource(Source<I> src, Mapper<I, O> mapper) {
        this.src = src;
        this.mapper = mapper;
    }

    @Override
    public O get() {
        return mapper.apply(src.get());
    }

    @Override
    public void reset(boolean temp) {
        src.reset(temp);
    }

}
