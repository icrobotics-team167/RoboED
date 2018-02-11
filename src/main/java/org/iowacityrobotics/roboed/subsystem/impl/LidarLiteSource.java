package org.iowacityrobotics.roboed.subsystem.impl;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalSource;
import org.iowacityrobotics.roboed.data.source.Source;

/**
 * A source implementation that reads distance from a LIDAR Lite unit on a DIO port.
 *
 * @author Evan Geng
 */
public class LidarLiteSource extends Source<Double> {

    /**
     * A pulse counter that measures pulse length.
     */
    private final Counter counter;

    /**
     * The constant converting pulse length in microseconds to a distance.
     */
    private final double conversionConst;

    /**
     * Creates a new LIDAR Lite source.
     * @param source The DIO signal provider the LIDAR unit is running on.
     * @param conversionConst The constant of conversion from pulse length microseconds to distance.
     */
    public LidarLiteSource(DigitalSource source, double conversionConst) {
        this.counter = new Counter(source);
        this.counter.setMaxPeriod(1.0);
        this.counter.setSemiPeriodMode(true);
        this.counter.reset();
        this.conversionConst = conversionConst;
    }

    @Override
    public Double get() {
        return counter.get() < 1 ? 0D : (counter.getPeriod() * conversionConst);
    }

}
