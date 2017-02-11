package org.iowacityrobotics.roboed.data.sink;

import org.iowacityrobotics.roboed.data.source.ISource;
import org.iowacityrobotics.roboed.data.UnboundException;

/**
 * Partial implementation of {@link ISink}.
 * @author Evan Geng
 */
public abstract class AbstractSink<T> implements ISink<T> {

    /**
     * The currently bound data source.
     */
    private ISource<T> bound;

    @Override
    public void bind(ISource<T> src) {
        bound = src;
    }

    @Override
    public void reset(boolean temp) {
        bound = null;
    }

    @Override
    public void tick() {
        if (bound == null)
            throw new UnboundException();
        process(bound.get());
    }

    /**
     * Process one frame of data.
     * @param data The data to process.
     */
    protected abstract void process(T data);

}
