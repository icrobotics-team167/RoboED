package org.iowacityrobotics.roboed.api.sensor;

import org.iowacityrobotics.roboed.api.abst.IObservableGetter;
import org.iowacityrobotics.roboed.api.abst.IPorted;

/**
 * Represents an input interface with the robot (e.g. a controller or limit switch).
 * @param <T> The type of data supplied by this device.
 * @author Evan Geng
 */
public interface ISensor<T> extends IObservableGetter<T>, IPorted { }
