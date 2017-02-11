package org.iowacityrobotics.roboed.data.mapper;

import org.iowacityrobotics.roboed.data.source.ISource;

/**
 * The implementation of {@link ISource#map}.
 * @author Evan Geng
 */
public final class MappingSource<I, O> implements ISource<O> {

    /**
     * The backing data source.
     */
    private final ISource<I> src;

    /**
     * The mapping function.
     */
    private final IMapper<I, O> mapper;

    /**
     * Creates a new mapping pipeline element.
     * @param src The backing data source.
     * @param mapper The mapping function.
     */
    public MappingSource(ISource<I> src, IMapper<I, O> mapper) {
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
