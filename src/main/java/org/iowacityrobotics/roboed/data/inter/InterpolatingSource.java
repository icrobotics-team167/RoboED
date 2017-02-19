package org.iowacityrobotics.roboed.data.inter;

import org.iowacityrobotics.roboed.data.source.Source;

/**
 * The implementation of {@link Source#inter}.
 * @author Evan Geng
 */
public final class InterpolatingSource<I1, I2, O> implements Source<O> {

    /**
     * The first backing data source.
     */
    private final Source<I1> src1;

    /**
     * The second backing data source.
     */
    private final Source<I2> src2;

    /**
     * The interpolation function.
     */
    private final Interpolator<I1, I2, O> inter;

    /**
     * Creates a new interpolating pipeline segment.
     * @param src1 The first data source.
     * @param src2 The second data source.
     * @param inter The interpolation function.
     */
    public InterpolatingSource(Source<I1> src1, Source<I2> src2, Interpolator<I1, I2, O> inter) {
        this.src1 = src1;
        this.src2 = src2;
        this.inter = inter;
    }

    @Override
    public O get() {
        return inter.apply(src1.get(), src2.get());
    }

    @Override
    public void reset(boolean temp) {
        src1.reset(temp);
        src2.reset(temp);
    }

}
