package org.iowacityrobotics.roboed.data;

/**
 * Represents a data pipeline element that may have persistent state.
 * @author Evan Geng
 */
public interface IStatefulData {

    /**
     * Reset this data source's state.
     * @param temp Whether the reset was due to a temporary operation mode or not.
     */
    default void reset(boolean temp) {
        // NO-OP
    }

}
