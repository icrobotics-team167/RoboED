package org.iowacityrobotics.roboed.util.primitive;

/**
 * A {@link java.util.function.BiConsumer BiConsumer} that takes the primitive <code>int</code> as a parameter.
 * @param <T> The other object's type.
 * @author Evan Geng
 */
@FunctionalInterface
public interface IIntTBiConsumer<T> {

    /**
     * Processes the given values.
     * @param a The integer A.
     * @param b The object B.
     */
    void accept(int a, T b);

}
