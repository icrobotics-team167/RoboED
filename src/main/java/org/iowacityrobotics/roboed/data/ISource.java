package org.iowacityrobotics.roboed.data;

/**
 * Provides data.
 * @author Evan Geng
 */
@FunctionalInterface
public interface ISource<T> extends IStatefulData {

    /**
     * Retrieve data from this source.
     * @return The data.
     */
    T get();

    /**
     * Creates a mapping pipeline segment.
     * @param mapper The mapping function.
     * @param <O> The output data type.
     * @return The newly-created pipeline segment.
     */
    default <O> ISource<O> map(IMapper<T, O> mapper) {
        return new MappingSource<>(this, mapper); // TODO Impl
    }

    /**
     * Creates an interpolating pipeline segment.
     * @param src The other data source.
     * @param inter The interpolating function.
     * @param <V> The other data source's type.
     * @param <O> The output data type.
     * @return The newly-created pipeline segment.
     */
    default <V, O> ISource<O> inter(ISource<V> src, IInterpolator<T, V, O> inter) {
        return new InterpolatingSource<>(this, src, inter);
    }

}
