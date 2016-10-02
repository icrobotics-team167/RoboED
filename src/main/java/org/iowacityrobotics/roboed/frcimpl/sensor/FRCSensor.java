package org.iowacityrobotics.roboed.frcimpl.sensor;

import org.iowacityrobotics.roboed.api.abst.IDataBinding;
import org.iowacityrobotics.roboed.api.sensor.ISensor;
import org.iowacityrobotics.roboed.util.function.Lambdas;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.BiConsumer;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public abstract class FRCSensor<T> implements ISensor<T> {

    private final int id;
    private BiConsumer<T, T> onMutation;
    private Collection<FRCDataPipeline.MappingPipelineRoot> bindings;

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
        onMutation.andThen(handler);
    }

    @Override
    public IDataBinding binding() {
        FRCDataPipeline.MappingPipelineRoot<T> binding = new FRCDataPipeline.MappingPipelineRoot<>(this);
        bindings.add(binding);
        return binding;
    }

    public void tick() {
        bindings.forEach(FRCDataPipeline.MappingPipelineRoot::update);
    }

}
