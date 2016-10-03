package org.iowacityrobotics.roboed.frcimpl.auto;

import java.util.HashMap;
import java.util.Map;

import org.iowacityrobotics.roboed.api.auto.IAutoManager;
import org.iowacityrobotics.roboed.api.auto.IAutoRoutine;
import org.iowacityrobotics.roboed.frcimpl.event.FRCEventBus;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCAutoManager implements IAutoManager {

    private Map<String, FRCAutoRoutine> routines;
    private FRCAutoRoutine selectedRoutine;

    public FRCAutoManager() {
        this.routines = new HashMap<>();
    }

    @Override
    public IAutoRoutine createRoutine(String id) {
        FRCAutoRoutine routine = new FRCAutoRoutine(this, id);
        routines.put(id, routine);
        return routine;
    }

    @Override
    public void setRoutine(String id) {
        if (routines.containsKey(id))
            selectedRoutine = routines.get(id);
        throw new IllegalArgumentException("No such autonomous routine!");
    }

    public void tick(FRCEventBus eventBus) {
        // eventBus.post(new AutoTickEvent(selectedRoutine)); Is there a real reason to have an event here?
        
    }

}
