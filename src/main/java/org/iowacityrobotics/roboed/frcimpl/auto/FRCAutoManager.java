package org.iowacityrobotics.roboed.frcimpl.auto;

import java.util.HashMap;
import java.util.Map;

import org.iowacityrobotics.roboed.api.auto.IAutoManager;
import org.iowacityrobotics.roboed.api.auto.IAutoRoutine;
import org.iowacityrobotics.roboed.util.Pair;
import org.iowacityrobotics.roboed.util.function.ICondition;
import org.iowacityrobotics.roboed.util.function.IConditionFactory;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCAutoManager implements IAutoManager {

    private Map<String, FRCAutoRoutine> routines;
    private FRCAutoRoutine selectedRoutine;
    private ICondition condition;
    private Runnable action;
    private int actionCount, actionIndex;
    private boolean running;

    public FRCAutoManager() {
        this.routines = new HashMap<>();
    }

    @Override
    public IAutoRoutine createRoutine(String id) {
        if (!routines.containsKey(id)) {
            FRCAutoRoutine routine = new FRCAutoRoutine(this, id);
            routines.put(id, routine);
            return routine;
        }
        throw new IllegalArgumentException("Autonomous routine \"" + id + "\" already exists!");
    }

    @Override
    public void setRoutine(String id) {
        if (!running) {
            if (routines.containsKey(id))
                selectedRoutine = routines.get(id);
            throw new IllegalArgumentException("No such autonomous routine \"" + id + "\"!");
        }
        else
            throw new IllegalStateException("Cannot change routine while running autonomous!");
    }
    
    public void autonomousStart() {
        if (selectedRoutine != null) {
            actionCount = selectedRoutine.actions.size();
            if (running = actionCount != 0) {
                actionIndex = -1;
                nextAction();
            }
        }
        else
            running = false;
    }

    public void tick() {
        if (running) {
            if (actionIndex < actionCount) {
                if (condition.isMet())
                    action.run();
                else
                    nextAction();
            }
            else
                running = false;
        }
    }
    
    private void nextAction() {
        Pair<IConditionFactory, Runnable> pair = selectedRoutine.actions.get(++actionIndex);
        condition = pair.getA().create();
        action = pair.getB();
    }

}
