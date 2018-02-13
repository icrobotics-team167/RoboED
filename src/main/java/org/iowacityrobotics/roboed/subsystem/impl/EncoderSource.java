package org.iowacityrobotics.roboed.subsystem.impl;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import org.iowacityrobotics.roboed.data.source.Source;
import org.iowacityrobotics.roboed.robot.Devices;
import org.iowacityrobotics.roboed.util.collection.StackNode;

/**
 * A source representing the count of an encoder.
 * @author Evan Geng
 */
public class EncoderSource extends Source<Integer> {

    /**
     * The encoder instance this source draws data from.
     */
    private final Encoder encoder;

    /**
     * The real count from the encoder on the previous frame.
     */
    private int realPreviousFrame;

    /**
     * The count stored by this source.
     */
    private int count;

    /**
     * The state stack for this stateful source.
     */
    private StackNode<Integer> state = null;

    /**
     * Creates a source for an encoder.
     * @param port1 The encoder's first DIO port.
     * @param port2 The encoder's second DIO port.
     * @param type The counting method used by the encoder.
     */
    public EncoderSource(int port1, int port2, CounterBase.EncodingType type) {
        this.encoder = new Encoder(Devices.dioInput(port1), Devices.dioInput(port2), false, type);
        this.realPreviousFrame = encoder.get();
        this.count = 0;
    }

    @Override
    public Integer get() {
        int currentCount = encoder.get();
        count += (currentCount - realPreviousFrame);
        realPreviousFrame = currentCount;
        return count;
    }

    @Override
    public void reset(boolean temp) {
        count = 0;
    }

    @Override
    public void pushState() {
        state = state == null ? new StackNode<>(count) : state.extend(count);
    }

    @Override
    public void popState() {
        count = state.getValue();
        state = state.getParent();
    }

}
