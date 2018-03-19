package org.iowacityrobotics.roboed.subsystem;

import org.iowacityrobotics.roboed.data.inter.Interpolator;
import org.iowacityrobotics.roboed.data.mapper.Mapper;
import org.iowacityrobotics.roboed.subsystem.impl.ResetSetInterpolator;
import org.iowacityrobotics.roboed.subsystem.impl.RisingEdgeMapper;
import org.iowacityrobotics.roboed.subsystem.impl.StateCounterMapper;
import org.iowacityrobotics.roboed.subsystem.impl.StateLatchMapper;

/**
 * Various simple stateful pipeline node implementations.
 * @author Evan Geng
 */
public class StateMachines {

    /**
     * Creates a new reset-set latch that outputs distinct constant value while set or unset.
     * Order of data matters! The left input resets the latch and the right input sets it.
     * @param off The value to output while unset.
     * @param on The value to output while set.
     * @param <T> The type of value to be output.
     * @return The newly-created reset-set latch.
     */
    public static <T> Interpolator<Boolean, Boolean, T> rsLatch(T off, T on) {
        return new ResetSetInterpolator<>(off, on);
    }

    /**
     * Creates a rising-edge gate that only outputs true on the frame when input rises from false to true.
     * @return The newly-created rising-edge gate.
     */
    public static Mapper<Boolean, Boolean> risingEdge() {
        return new RisingEdgeMapper();
    }

    /**
     * Creates a register that begins outputting a different value each time input rises from false to true.
     * Once the last value is reached, additional rising edges do nothing.
     * @param initial The value to return initially.
     * @param values The values to return on each state change.
     * @param <T> The type of value to return.
     * @return The newly-created state counter.
     */
    public static <T> Mapper<Boolean, T> stateCounter(T initial, Object... values) {
        return new StateCounterMapper<>(initial, values);
    }

    /**
     * Creates a latch that stores and outputs states marked "non-empty". Initially has "empty" state.
     * @param empty The "empty" state in the enum.
     * @param <T> The enum type to store.
     * @return The newly-created latch.
     */
    public static <T extends Enum<T>> Mapper<T, T> stateLatch(T empty) {
        return stateLatch(empty, empty);
    }

    /**
     * Creates a latch that stores and outputs states marked "non-empty".
     * @param empty The "empty" state in the enum.
     * @param initial The initial state to store.
     * @param <T> The enum type to store.
     * @return The newly-created latch.
     */
    public static <T extends Enum<T>> Mapper<T, T> stateLatch(T empty, T initial) {
        return new StateLatchMapper<>(empty, initial);
    }

}