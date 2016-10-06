package org.iowacityrobotics.roboed.frcimpl.sensor;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.BiConsumer;

import org.iowacityrobotics.roboed.api.abst.IDataBinding;
import org.iowacityrobotics.roboed.api.sensor.ISensor;
import org.iowacityrobotics.roboed.util.function.Lambdas;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public abstract class FRCSensor<T> implements ISensor<T> {

    private final int id;
    private BiConsumer<T, T> onMutation;
    private T cached;
    private Collection<FRCDataPipeline.MappingPipelineRoot<T>> bindings;

    protected FRCSensor(int id) {
        this.id = id;
        this.onMutation = Lambdas.noopBinary();
        this.bindings = new HashSet<>();
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void onMutation(BiConsumer<T, T> handler) {
        onMutation = onMutation.andThen(handler);
    }
    
    @Override
    public T get() {
        T value = doGet();
        if (value != cached) {
            onMutation.accept(cached, value);
            cached = value;
        }
        return value;
    }
    
    abstract T doGet();

    @Override
    public IDataBinding<T> binding() {
        FRCDataPipeline.MappingPipelineRoot<T> binding = new FRCDataPipeline.MappingPipelineRoot<>(this);
        bindings.add(binding);
        return binding;
    }

    public void tick() {
        bindings.forEach(FRCDataPipeline.MappingPipelineRoot::update);
    }

}
