package org.iowacityrobotics.roboed.api.subsystem;

import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;

/**
 * Represents a subsystem of a robot that optionally takes an input or returns an output.
 * Output-only elements are known as "source elements", 
 * input-output elements are known as "intermediate elements",
 * and input-only elements are known as "terminal elements".
 * @author Evan Geng
 */
public interface ISubsystem<I, O> {
    
    /**
     * Gets the type of this subsystem.
     * @return The subsystem's type.
     */
    ISubsystemType<I, O, ? extends ISubsystemProvider<I, O>> getType();
    /**
     * Binds a data stream to this subsystem to serve as the input stream.
     * @param input The input stream.
     * @throws UnsupportedOperationException If this pipeline is a source element.
     */
    void bind(IDataSource<I> input);

    /**
     * Gets the output stream for this subsystem, if one exists.
     * @return The output stream.
     * @throws UnsupportedOperationException If this pipeline is a terminal element.
     */
    IDataSource<O> output();
    
    /**
     * Wipes the state of this subsystem and unbinds any input, if it exists.
     */
    void reset();
    
}
