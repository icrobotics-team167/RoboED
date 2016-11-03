package org.iowacityrobotics.roboed.impl.data;

import java.util.function.Function;

import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.data.MappingPipeline;

import edu.wpi.first.wpilibj.AnalogInput;

/** 
 * @author Evan Geng
 */
public class AnalogDataSource implements IDataSource<Double> {

    private final AnalogInput source;
    
    public AnalogDataSource(AnalogInput source) {
        this.source = source;
    }
    
    @Override
    public <V> IDataSource<V> map(Function<Double, V> mapper) {
        return new MappingPipeline<>(this, mapper);
    }

    @Override
    public Double request() {
        return source.getAverageVoltage();
    }

}
