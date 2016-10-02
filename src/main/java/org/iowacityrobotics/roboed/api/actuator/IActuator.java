package org.iowacityrobotics.roboed.api.actuator;

import org.iowacityrobotics.roboed.api.abst.IObservableSetter;
import org.iowacityrobotics.roboed.api.abst.IPorted;

/**
 * Represents an output interface with the robot (e.g. a motor or pneumatic cylinder).
 * @param <T> The type of data accepted by this device.
 * @author Evan Geng
 */
public interface IActuator<T> extends IObservableSetter<T>, IPorted { }
