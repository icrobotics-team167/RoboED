package org.iowacityrobotics.roboed.util.primitive;

/**
 * A {@link java.util.function.BiFunction BiFunction} that takes the primitive <code>int</code> as a parameter.
 * @param <I> The other input object's type.
 * @param <O> The output type.
 * @author Evan Geng
 */
@FunctionalInterface
public interface IIntTBiFunction<I, O> {

    /**
     * Processes the given values.
     * @param a The integer A.
     * @param b The object B.
     */
    O apply(int a, I b);

}
