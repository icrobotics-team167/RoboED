package org.iowacityrobotics.roboed.frcimpl.sensor;

import org.iowacityrobotics.roboed.api.abst.IDataBinding;
import org.iowacityrobotics.roboed.api.abst.IObservableSetter;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class FRCDataPipeline<T> implements IDataBinding<T> {

    Consumer<T> sink;

    @Override
    public <O> IDataBinding<O> map(Function<T, O> mapper) {
        MappingPipelineNode<T, O> downstream = new MappingPipelineNode<>(mapper);
        sink = downstream;
        return downstream;
    }

    @Override
    public void bind(IObservableSetter<T> setter) {
        this.sink = setter;
    }

    public static class MappingPipelineRoot<T> extends FRCDataPipeline<T> {

        Supplier<T> source;

        public MappingPipelineRoot(Supplier<T> source) {
            this.source = source;
        }

        public void update() {
            if (sink != null)
                sink.accept(source.get());
            else
                throw new IllegalStateException("Unbound data pipeline!");
        }

    }

    private static class MappingPipelineNode<I, O> extends FRCDataPipeline<O> implements Consumer<I> {

        private final Function<I, O> mapper;

        MappingPipelineNode(Function<I, O> mapper) {
            this.mapper = mapper;
        }

        @Override
        public void accept(I i) {
            sink.accept(mapper.apply(i));
        }

    }

}
