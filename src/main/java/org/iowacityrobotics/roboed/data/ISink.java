package org.iowacityrobotics.roboed.data;

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

}
