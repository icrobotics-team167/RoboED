package org.iowacityrobotics.roboed.data.sink;

import org.iowacityrobotics.roboed.data.Data;
import org.iowacityrobotics.roboed.data.IStatefulData;
import org.iowacityrobotics.roboed.data.mapper.Mapper;
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
     * Creates a new mapper wrapping this one by superimposing a mapper.
     * @param mapper The mapper data should be processed by.
     * @param <V> The data type to map from.
     * @return The new pipeline element.
     */
    public <V> Sink<V> map(Mapper<V, T> mapper) {
        return new MapDelegateSink<>(this, mapper);
    }

    /**
     * Creates a new sink that distributes one value to multiple downstream sinks.
     * @param other The other sink to distribute data to.
     * @return The new pipeline element.
     */
    public Sink<T> join(Sink<T> other) {
        return new JoiningSink<>(this, other);
    }

    /**
     * Tells this sink to read and consume one frame of data.
     */
    private void tick() {
        if (bound == null)
            noData();
        else
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

    /**
     * A sink implementation that superimposes a mapping function onto another sink.
     */
    private static class MapDelegateSink<V, T> extends Sink<V> {

        private final Sink<T> downstream;
        private final Mapper<V, T> mapper;

        MapDelegateSink(Sink<T> downstream, Mapper<V, T> mapper) {
            this.mapper = mapper;
            this.downstream = downstream;
        }

        public void process(V data) {
            downstream.process(mapper.apply(data));
        }

        @Override
        public void noData() {
            downstream.noData();
        }

    }

    /**
     * A sink implementation that distributes one value to multiple downstream sinks.
     */
    private static class JoiningSink<T> extends Sink<T> {

        private final Sink<T> dsA, dsB;

        JoiningSink(Sink<T> dsA, Sink<T> dsB) {
            this.dsA = dsA;
            this.dsB = dsB;
        }

        public void process(T data) {
            dsA.process(data);
            dsB.process(data);
        }

        @Override
        public void noData() {
            dsA.noData();
            dsB.noData();
        }

    }

}
