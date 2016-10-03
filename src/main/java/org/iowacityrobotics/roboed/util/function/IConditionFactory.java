package org.iowacityrobotics.roboed.util.function;

/**
 * Represents a factory function for an {@link ICondition}.
 * @author Evan Geng
 */
@FunctionalInterface
public interface IConditionFactory {

    /**
     * Creates a new condition.
     * @return The newly-created condition.
     */
    ICondition create();
    
    /**
     * Creates a new condition factory that generates the logical negation (NOT operation) of the condition.
     * @return The logical negation.
     */
    default IConditionFactory negate() {
        return () -> create().negate();
    }

    /**
     * Creates a new condition factory that generates the logical conjunction (AND operation) of the condition and another condition.
     * @param o The other condition factory.
     * @return The logical conjunction.
     */
    default IConditionFactory and(IConditionFactory o) {
        return () -> create().and(o.create());
    }

    /**
     * Creates a new condition factory that generates the logical inclusive disjunction (OR operation) of the condition and another condition.
     * @param o The other condition factory.
     * @return The logical inclusive disjunction.
     */
    default IConditionFactory or(IConditionFactory o) {
        return () -> create().or(o.create());
    }

    /**
     * Creates a new condition factory that generates the logical exclusive disjunction (XOR operation) of the condition and another condition.
     * @param o The other condition factory.
     * @return The logical exclusive disjunction.
     */
    default IConditionFactory xor(IConditionFactory o) {
        return () -> create().xor(o.create());
    }
    
    /**
     * Creates a new condition factory that returns the given value for every call to {@link #create()}.
     * @param condition The condition to be returned by the new factory.
     * @return The new factory.
     */
    static IConditionFactory getter(ICondition condition) {
        return () -> condition;
    }
    
}
