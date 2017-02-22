package org.iowacityrobotics.roboed.data.mapper;

import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.IStatefulData;

/**
 * A function that maps data of one type to the same or another type.
 * @author Evan Geng
 */
public abstract class Mapper<I, O> implements IStatefulData {

    /**
     * Constructs a mapper.
     */
    public Mapper() {
        if (!(this instanceof Data.StatelessMapper))
            Data.registerStateful(this);
    }

    /**
     * Converts data of one type to another or performs a unary operation.
     * @param data The input data.
     * @return The output data.
     */
    public abstract O apply(I data);

}
