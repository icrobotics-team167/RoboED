package org.iowacityrobotics.roboed.impl.operations;

import org.iowacityrobotics.roboed.api.RobotMode;
import org.iowacityrobotics.roboed.api.operations.IOperationsManager;
import org.iowacityrobotics.roboed.api.operations.InvalidOpModeException;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;
import org.iowacityrobotics.roboed.util.collection.StackNode;
import org.iowacityrobotics.roboed.util.function.ICondition;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author Evan Geng
 */
public class FRCOpManager implements IOperationsManager {

    private final FRCSysRegistry sysReg;
    private final Map<String, FRCOpMode> registry = new HashMap<>();
    private final Map<RobotMode, String> defaults = new HashMap<>();
    private StackNode<OpThread> running;

    public FRCOpManager(FRCSysRegistry sysReg) {
        this.sysReg = sysReg;
    }
    
    @Override
    public FRCOpMode getOpMode(String id) {
        if (id.equals(FRCOpMode.OP_NONE))
            return null;
        return registry.computeIfAbsent(id, k -> new FRCOpMode());
    }

    @Override
    public void setDefaultOpMode(RobotMode status, String id) {
        if (id == null || id.equals(FRCOpMode.OP_NONE))
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
            Thread.currentThread().interrupt();
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
        OpThread opThread = new OpThread(opmode, mode);
        running = running.extend(opThread);
        opThread.start();
    }
    
    private void wipeAndRun(String opmode) {
        while (running.hasParent()) {
            if (running.getValue() != Thread.currentThread())
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
    
    private class OpThread extends Thread {
        
        final FRCOpMode mode;
        final Object lock = new Object();
        
        OpThread(String modeName, FRCOpMode mode) {
            super("OpThread: " + modeName);
            this.mode = mode;
        }
        
        @Override
        public void run() {
            System.out.println("opmode thread run: " + this.getName());
            mode.onInit.run();
            ICondition condition = mode.doWhile.create();
            while (condition.isMet())
                sysReg.tick();
            mode.onDone.run();
            if (mode.next == null) {
                running = running.getParent();
                synchronized (lock) {
                    lock.notifyAll();
                }
            } else {
                wipeAndRun(mode.next);
            }
        }
        
    }

}
