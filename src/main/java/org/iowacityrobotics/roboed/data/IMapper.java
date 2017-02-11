package org.iowacityrobotics.roboed.data;

/**
 * A function that maps data of one type to the same or another type.
 * @author Evan Geng
 */
@FunctionalInterface
public interface IMapper<I, O> extends IStatefulData {

    /**
     * Converts data of one type to another or performs a unary operation.
     * @param data The input data.
     * @return The output data.
     */
    O apply(I data);

}
