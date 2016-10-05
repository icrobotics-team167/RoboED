package org.iowacityrobotics.roboed.util.function;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.iowacityrobotics.roboed.util.primitive.IntTMap;

/**
 * Utility class for creating various anonymous functions.
 * @author Evan Geng
 */
public class Lambdas {

    /**
     * @return A {@link Runnable} that does nothing.
     */
    public static Runnable noopNullary() {
        return () -> {};
    }

    /**
     * @param <A> The type of object to be consumed.
     * @return A {@link Consumer} that does nothing.
     */
    public static <A> Consumer<A> noopUnary() {
        return a -> {};
    }

    /**
     * @param <A> Object A to be consumed.
     * @param <B> Object B to be consumed.
     * @return A {@link BiConsumer} that does nothing.
     */
    public static <A, B> BiConsumer<A, B> noopBinary() {
        return (a, b) -> {};
    }

    /**
     * Composes two zero-argument functions (i.e. {@link Runnable}s).
     * @param a Function A.
     * @param b Function B.
     * @return The functional composition.
     */
    public static Runnable compose(Runnable a, Runnable b) {
        return () -> {
            a.run();
            b.run();
        };
    }

    /**
     * @return An {@link IntBinaryOperator} for the bitwise OR operator.
     */
    public static IntBinaryOperator bitOr() {
        return (a, b) -> a | b;
    }

    /**
     * @return An {@link IntBinaryOperator} for the bitwise XOR operator.
     */
    public static IntBinaryOperator bitXor() {
        return (a, b) -> a ^ b;
    }

    /**
     * @return An {@link IntBinaryOperator} for the bitwise AND operator.
     */
    public static IntBinaryOperator bitAnd() {
        return (a, b) -> a & b;
    }

    /**
     * @param <T> The type of object to be tested.
     * @return A predicate that returns <code>true</code> for any input.
     */
    public static <T> Predicate<T> acceptAll() {
        return x -> true;
    }

    /**
     * @param <T> The type of object to be tested.
     * @return A predicate that returns <code>false</code> for any input.
     */
    public static <T> Predicate<T> acceptNone() {
        return x -> false;
    }

    /**
     * @param <T> The type of object to be tested.
     * @return A predicate that requires objects to be non-null.
     */
    public static <T> Predicate<T> nonNull() {
        return x -> x != null;
    }
    
    /**
     * Creates a getter function for the given value.
     * @param value Underlying value to supply.
     * @return The new {@link Supplier}.
     */
    public static <T> Supplier<T> getter(T value) {
        return () -> value;
    }
    
    /**
     * Creates a {@link java.util.stream.Stream Stream} consumer that adds each stream element to a map.
     * @param map The map to add elements to.
     * @param mapper The key-to-value mapping function.
     * @return The created consumer.
     */
    public static <K, V> Consumer<K> into(Map<K, V> map, Function<K, V> mapper) {
        return k -> map.put(k, mapper.apply(k));
    }
    
    /**
     * Creates a {@link java.util.stream.Stream Stream} consumer that adds each stream element to a map.
     * @param map The map to add elements to.
     * @param mapper The key-to-value mapping function.
     * @return The created consumer.
     */
    public static <T> IntConsumer into(IntTMap<T> map, IntFunction<T> mapper) {
        return k -> map.put(k, mapper.apply(k));
    }

}
