package org.iowacityrobotics.roboed.data;

/**
 * The implementation of {@link ISource#inter}.
 * @author Evan Geng
 */
public class InterpolatingSource<I1, I2, O> implements ISource<O> {

    /**
     * The first backing data source.
     */
    private final ISource<I1> src1;

    /**
     * The second backing data source.
     */
    private final ISource<I2> src2;

    /**
     * The interpolation function.
     */
    private final IInterpolator<I1, I2, O> inter;

    /**
     * Creates a new interpolating pipeline segment.
     * @param src1 The first data source.
     * @param src2 The second data source.
     * @param inter The interpolation function.
     */
    public InterpolatingSource(ISource<I1> src1, ISource<I2> src2, IInterpolator<I1, I2, O> inter) {
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
