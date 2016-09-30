package org.iowacityrobotics.roboed.api.actuator;

import org.iowacityrobotics.roboed.api.abst.IObservableSetter;
import org.iowacityrobotics.roboed.api.abst.IPorted;

public interface IActuator<T> extends IObservableSetter<T>, IPorted { }
