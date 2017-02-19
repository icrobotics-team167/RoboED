package org.iowacityrobotics.roboed.data.sink;

import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.IStatefulData;
import org.iowacityrobotics.roboed.data.source.Source;
import org.iowacityrobotics.roboed.util.collection.StackNode;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Consumes data.
 * @author Evan Geng
 */
public abstract class Sink<T> implements IStatefulData {

    /**
     * Collection of all sinks.
     */
    private static final Collection<Sink> sinks = new LinkedList<>();

    /**
     * The stack of stored states.
     */
    private StackNode<Source<T>> state;

    /**
     * The currently bound data source.
     */
    private Source<T> bound;

    /**
     * Constructs a sink.
     */
    public Sink() {
        Data.registerStateful(this);
        sinks.add(this);
    }

    /**
     * Binds a data source to pull data from.
     * @param src The source to be bound.
     */
    public void bind(Source<T> src) {
        bound = src;
    }

    /**
     * Tells this sink to read and consume one frame of data.
     */
    public void tick() {
        if (bound == null)
            noData();
        process(bound.get());
    }

    @Override
    public void reset(boolean temp) {
        bound = null;
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
     * Ticks all sinks.
     */
    public static void tickAll() {
        sinks.forEach(Sink::tick);
    }

}
