package org.iowacityrobotics.roboed.data.sink;

import org.iowacityrobotics.roboed.data.source.ISource;
import org.iowacityrobotics.roboed.data.IStatefulData;

/**
 * Consumes data.
 * @author Evan Geng
 */
public interface ISink<T> extends IStatefulData {

    /**
     * Binds a data source to pull data from.
     * @param src The source to be bound.
     */
    void bind(ISource<T> src);

    /**
     * Tells this sink to read and consume one frame of data.
     */
    void tick();

}
