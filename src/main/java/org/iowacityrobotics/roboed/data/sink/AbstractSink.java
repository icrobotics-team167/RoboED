package org.iowacityrobotics.roboed.data.sink;

import org.iowacityrobotics.roboed.data.source.ISource;
import org.iowacityrobotics.roboed.util.collection.StackNode;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Partial implementation of {@link ISink}.
 * @author Evan Geng
 */
public abstract class AbstractSink<T> implements ISink<T> {

    private static final Collection<AbstractSink> sinks = new LinkedList<>();

    /**
     * The stack of stored states.
     */
    private StackNode<ISource<T>> state;

    /**
     * The currently bound data source.
     */
    private ISource<T> bound;

    /**
     * Constructs a sink, registering it to be ticked.
     */
    public AbstractSink() {
        sinks.add(this);
    }

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
            noData();
        process(bound.get());
    }

    @Override
    public void pushState() {
        state = state == null ? new StackNode<>(bound) : state.extend(bound);
    }

    @Override
    public void popState() {
        bind(state.getValue());
        state = state.getParent();
    }

    /**
     * Process one frame of data.
     * @param data The data to process.
     */
    protected abstract void process(T data);

    /**
     * If no binding is present, executes this instead.
     */
    protected void noData() {
        // NO-OP
    }

    /**
     * Resets all sinks.
     * @param temp Whether the reset was due to a temporary operation mode or not.
     */
    public static void resetAll(boolean temp) {
        sinks.forEach(s -> s.reset(temp));
    }

    /**
     * Tick all sinks.
     */
    public static void tickAll() {
        sinks.forEach(ISink::tick);
    }

    /**
     * Push the state of all sinks.
     */
    public static void pushStateAll() {
        sinks.forEach(ISink::pushState);
    }

    /**
     * Pop the state of all sinks.
     */
    public static void popStateAll() {
        sinks.forEach(ISink::popState);
    }

}
