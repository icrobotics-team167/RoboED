package org.iowacityrobotics.roboed.data;

/**
 * Represents a data pipeline element that may have persistent state.
 * @author Evan Geng
 */
public interface IStatefulData {

    /**
     * Reset this data object's state.
     * @param temp Whether the reset was due to a temporary operation mode or not.
     */
    default void reset(boolean temp) {
        // NO-OP
    }

    /**
     * Stores the currently bound data binding state on a stack.
     */
    default void pushState() {
        // NO-OP
    }

    /**
     * Pops the top state stack element, restoring its bindings.
     */
    default void popState() {
        // NO-OP
    }

}
