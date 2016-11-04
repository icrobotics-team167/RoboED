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

    @Override
    public FRCOpMode setTemporary() {
        this.next = null;
        return this;
    }

    @Override
    public FRCOpMode setNext(String id) {
        this.next = id;
        return this;
    }

    @Override
    public FRCOpMode onInit(Runnable executor) {
        onInit.addHandler(executor);
        return this;
    }

    @Override
    public FRCOpMode whileCondition(IConditionFactory factory) {
        doWhile = factory;
        return this;
    }

    @Override
    public FRCOpMode onDone(Runnable executor) {
        onDone.addHandler(executor);
        return this;
    }
    
    /**
     * Marks this opmode as immutable.
     */
    public void setImmutable() {
        
    }

}
