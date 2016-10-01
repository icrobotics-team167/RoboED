package org.iowacityrobotics.roboed.util.function;

@FunctionalInterface
public interface ICondition {

    boolean isMet();

    default ICondition negate() {
        return () -> !isMet();
    }

    default ICondition and(ICondition o) {
        return () -> isMet() && o.isMet();
    }

    default ICondition or(ICondition o) {
        return () -> isMet() || o.isMet();
    }

}
