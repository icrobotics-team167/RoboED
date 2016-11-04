package org.iowacityrobotics.roboed.impl.operations;

import java.util.HashMap;
import java.util.Map;

import org.iowacityrobotics.roboed.api.RobotMode;
import org.iowacityrobotics.roboed.api.operations.IOperationsManager;
import org.iowacityrobotics.roboed.api.operations.InvalidOpModeException;
import org.iowacityrobotics.roboed.util.collection.StackNode;

/** 
 * @author Evan Geng
 */
public class FRCOpManager implements IOperationsManager {
    
    private final Map<String, FRCOpMode> registry = new HashMap<>();
    private final Map<RobotMode, String> defaults = new HashMap<>();
    private StackNode<OpThread> running;
    
    @Override
    public FRCOpMode getOpMode(String id) {
        FRCOpMode opMode = registry.get(id);
        if (opMode == null) {
            opMode = new FRCOpMode();
            registry.put(id, opMode);
        }
        return opMode;
    }

    @Override
    public void setDefaultOpMode(RobotMode status, String id) {
        if (id == null || id == FRCOpMode.OP_NONE)
            defaults.remove(status);
        else
            defaults.put(status, id);
    }

    @Override
    public synchronized void setOpMode(String id) {
        if (!registry.containsKey(id))
            throw new InvalidOpModeException(id);
        if (registry.get(id).next == null) {
            startOpThread(id);
            waitForChild(running.getValue());
        } else {
            wipeAndRun(id);
        }
    }
    
    private void waitForChild(OpThread child) {
        synchronized (child.lock) {
            try {
                child.lock.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private void startOpThread(String opmode) {
        FRCOpMode mode = registry.get(opmode);
        OpThread opThread = new OpThread(() -> {
            // TODO Do the op and release the parent thread if it's locked
            /*
            synchronized (opThread.lock) {
                opThread.lock.notify();
            }
             */
        }, "OpThread: " + opmode);
        running = running.extend(opThread);
        opThread.start();
    }
    
    private void wipeAndRun(String opmode) {
        while (running.hasParent()) {
            running.getValue().interrupt();
            running = running.getParent();
        }
        startOpThread(opmode);
    }
    
    public void initialize() {
        FRCOpMode mode = getOpMode(FRCOpMode.OP_NOOP);
        mode.whileCondition(() -> true);
        mode.setImmutable();
        
        running = new StackNode<>();
        startOpThread(FRCOpMode.OP_NOOP);
    }
    
    public void modeChanged(RobotMode newMode) {
        wipeAndRun(defaults.containsKey(newMode) ? defaults.get(newMode) : FRCOpMode.OP_NOOP);
    }
    
    private static class OpThread extends Thread {
        
        Object lock;
        
        OpThread(Runnable runnable, String name) {
            super(runnable, name);
        }
        
    }

}
