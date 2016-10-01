package org.iowacityrobotics.roboed.frcimpl.auto;

import org.iowacityrobotics.roboed.api.auto.IAutoManager;
import org.iowacityrobotics.roboed.api.auto.IAutoRoutine;

import java.util.HashMap;
import java.util.Map;

public class FRCAutoManager implements IAutoManager {

    private Map<String, IAutoRoutine> routines;
    private String routineId;

    public FRCAutoManager() {
        this.routines = new HashMap<>();
    }

    @Override
    public IAutoRoutine createRoutine(String id) {
        IAutoRoutine routine = new FRCAutoRoutine(this, id);
        routines.put(id, routine);
        return routine;
    }

    @Override
    public void setRoutine(String id) {
        if (routines.containsKey(id))
            routineId = id;
        throw new IllegalArgumentException("No such autonomous routine!");
    }

}
