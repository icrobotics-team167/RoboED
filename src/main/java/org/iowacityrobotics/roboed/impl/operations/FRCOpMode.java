package org.iowacityrobotics.roboed.impl.operations;

import org.iowacityrobotics.roboed.api.operations.IOpMode;
import org.iowacityrobotics.roboed.impl.event.NullaryEventStream;
import org.iowacityrobotics.roboed.util.function.IConditionFactory;

/**
 * @author Evan Geng
 */
public class FRCOpMode implements IOpMode {

    public static final String OP_NONE = "builtin_op_none";
    public static final String OP_NOOP = "builtin_op_noop";
    
    String next = OP_NOOP;
    NullaryEventStream onInit;
    IConditionFactory doWhile;
    NullaryEventStream onDone;
    
    private boolean immutable = false;

    @Override
    public FRCOpMode setTemporary() {
        checkMutability();
        this.next = null;
        return this;
    }

    @Override
    public FRCOpMode setNext(String id) {
        checkMutability();
        this.next = id;
        return this;
    }

    @Override
    public FRCOpMode onInit(Runnable executor) {
        checkMutability();
        onInit.addHandler(executor);
        return this;
    }

    @Override
    public FRCOpMode whileCondition(IConditionFactory factory) {
        checkMutability();
        doWhile = factory;
        return this;
    }

    @Override
    public FRCOpMode onDone(Runnable executor) {
        checkMutability();
        onDone.addHandler(executor);
        return this;
    }
    
    public void setImmutable() {
        this.immutable = true;
    }
    
    private void checkMutability() {
        if (immutable)
            throw new UnsupportedOperationException("This OpMode cannot be modified!");
    }

}
