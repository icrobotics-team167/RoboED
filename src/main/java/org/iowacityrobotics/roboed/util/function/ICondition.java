package org.iowacityrobotics.roboed.util.function;

/**
 * Represents a boolean conditional.
 * @author Evan Geng
 */
@FunctionalInterface
public interface ICondition {

    /**
     * Checks whether this condition is met or not.
     * @return Whether this condition is met or not.
     */
    boolean isMet();

    /**
     * Creates a new condition that's the logical negation (NOT operation) of this one.
     * @return The logical negation.
     */
    default ICondition negate() {
        return () -> !isMet();
    }

    /**
     * Creates a new condition that's the logical conjunction (AND operation) of this and another condition.
     * @param o The other condition.
     * @return The logical conjunction.
     */
    default ICondition and(ICondition o) {
        return () -> isMet() && o.isMet();
    }

    /**
     * Creates a new condition that's the logical inclusive disjunction (OR operation) of this and another condition.
     * @param o The other condition.
     * @return The logical inclusive disjunction.
     */
    default ICondition or(ICondition o) {
        return () -> isMet() || o.isMet();
    }

    /**
     * Creates a new condition that's the logical exclusive disjunction (XOR operation) of this and another condition.
     * @param o The other condition.
     * @return The logical exclusive disjunction.
     */
    default ICondition xor(ICondition o) {
        return () -> isMet() != o.isMet();
    }

}
