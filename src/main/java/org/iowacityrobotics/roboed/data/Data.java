package org.iowacityrobotics.roboed.data;

import org.iowacityrobotics.roboed.data.sink.AbstractSink;
import org.iowacityrobotics.roboed.data.sink.ISink;
import org.iowacityrobotics.roboed.data.source.ISource;
import org.iowacityrobotics.roboed.util.collection.Pair;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Various lower-level data utilities.
 * @author Evan Geng
 */
public final class Data {

    /**
     * Creates a stateless sink using the given processor.
     * @param consumer The processor.
     * @param <T> The type of data to consume.
     * @return The new sink.
     */
    public static <T> ISink<T> sink(Consumer<T> consumer) {
        return new StatelessSink<>(consumer);
    }

    /**
     * A stateless implementation of {@link ISink that uses a {@link Consumer} to process data.
     * @author Evan Geng
     */
    private static final class StatelessSink<T> extends AbstractSink<T> {

        /**
         * The backing processor.
         */
        private final Consumer<T> consumer;

        /**
         * Creates a sink with the given processor.
         * @param consumer The backing processor.
         */
        public StatelessSink(Consumer<T> consumer) {
            this.consumer = consumer;
        }

        @Override
        protected void process(T data) {
            consumer.accept(data);
        }

    }

}
