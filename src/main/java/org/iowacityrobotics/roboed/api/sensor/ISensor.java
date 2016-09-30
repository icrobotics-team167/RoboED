package org.iowacityrobotics.roboed.api.sensor;

import org.iowacityrobotics.roboed.api.abst.IObservableGetter;
import org.iowacityrobotics.roboed.api.abst.IPorted;

public interface ISensor<T> extends IObservableGetter<T>, IPorted { }
