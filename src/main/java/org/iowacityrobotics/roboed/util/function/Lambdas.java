package org.iowacityrobotics.roboed.util.function;

import java.util.function.*;

public class Lambdas {

    public static Runnable noopNullary() {
        return () -> {};
    }

    public static <A> Consumer<A> noopUnary() {
        return a -> {};
    }

    public static <A, B> BiConsumer<A, B> noopBinary() {
        return (a, b) -> {};
    }

    public static Runnable compose(Runnable a, Runnable b) {
        return () -> {
            a.run();
            b.run();
        };
    }

    public static IntBinaryOperator bitOr() {
        return (a, b) -> a | b;
    }

    public static IntBinaryOperator bitXor() {
        return (a, b) -> a ^ b;
    }

    public static IntBinaryOperator bitAnd() {
        return (a, b) -> a & b;
    }

    public static <T> Predicate<T> acceptAll() {
        return x -> true;
    }

    public static <T> Predicate<T> acceptNone() {
        return x -> false;
    }

    public static <T> Predicate<T> nonNull() {
        return x -> x != null;
    }

}
