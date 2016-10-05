package org.iowacityrobotics.roboed.frcimpl.event;

import java.util.Arrays;

import org.iowacityrobotics.roboed.api.event.IButtonManager;
import org.iowacityrobotics.roboed.api.event.IEventBus;
import org.iowacityrobotics.roboed.api.event.button.ButtonPressEvent;
import org.iowacityrobotics.roboed.api.event.button.ButtonReleaseEvent;
import org.iowacityrobotics.roboed.util.function.Lambdas;
import org.iowacityrobotics.roboed.util.mutable.MutableBoolean;
import org.iowacityrobotics.roboed.util.primitive.IntTMap;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Part of the WPILib 2016 implementation of RoboED.
 * @author Evan Geng
 */
public class FRCButtonManager implements IButtonManager {
    
    private final IEventBus eventBus;
    private final DriverStation ds;
    private final IntTMap<IntTMap<MutableBoolean>> tracking;
    
    public FRCButtonManager(IEventBus eventBus, DriverStation ds) {
        this.eventBus = eventBus;
        this.ds = ds;
        this.tracking = new IntTMap<>();
    }
    
    @Override
    public void subscribe(int controllerPort, int... buttons) {
        IntTMap<MutableBoolean> ctrl = tracking.get(controllerPort);
        if (ctrl == null)
            tracking.put(controllerPort, ctrl = new IntTMap<>());
        Arrays.stream(buttons).forEach(Lambdas.into(ctrl, i -> new MutableBoolean()));
    }
    
    public void tick() {
        tracking.forEach((port, ctrl) -> {
            int btnState = ds.getStickButtons(port);
            ctrl.forEach((bId, cacheState) -> {
                if ((btnState & (1 << (bId - 1))) == 0) {
                    if (cacheState.getPrim()) {
                        cacheState.setPrim(false);
                        eventBus.post(new ButtonReleaseEvent(port, bId));
                    }
                } else {
                    if (!cacheState.getPrim()) {
                        cacheState.setPrim(true);
                        eventBus.post(new ButtonPressEvent(port, bId));
                    }
                }
            });
        });
    }

}
