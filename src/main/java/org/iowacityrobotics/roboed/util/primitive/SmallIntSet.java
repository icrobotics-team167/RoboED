package org.iowacityrobotics.roboed.util.primitive;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.iowacityrobotics.roboed.util.math.Maths;

/**
 * A set of the primitive type <code>int</code>. Stores numbers between 0 and 64.
 * @author Evan Geng
 */
public class SmallIntSet {

    /**
     * The bitmask that actually stores the set elements.
     */
    private long bitmask;
    
    public SmallIntSet() {
        this.bitmask = 0L;
    }
    
    /**
     * Adds an element to this set.
     * @param value The value to add to this set.
     * @return Whether an element was added or not in this operation.
     */
    public boolean add(int value) {
        if (!Maths.inRange(value, 0, 64))
            throw new IndexOutOfBoundsException("Value out of range: " + Integer.toString(value));
        if ((bitmask & (1 << value)) != 0)
            return false;
        bitmask = bitmask | (1 << value);
        return true;
    }

    /**
     * Removes the given element from this set.
     * @param value The value to remove from the set.
     * @return Whether an element was removed or not in this operation.
     */
    public boolean remove(int value) {
        if (!Maths.inRange(value, 0, 64))
            return false;
        if ((bitmask & (1 << value)) != 0) {
            bitmask = bitmask ^ (1 << value);
            return true;
        }
        return false;
    }

    /**
     * Removes all elements from this set.
     */
    public void clear() {
        bitmask = 0;
    }
    
    /**
     * Gets the number of elements in this set. This runs in O(64) time!
     * @return The number of elements in this set.
     */
    public int size() {
        int size = 0;
        for (int i = 0; i <= 64; i++) {
            if ((bitmask & (1 << i)) != 0)
                size++;
        }
        return size;
    }

    /**
     * Checks if this set has no elements.
     * @return Whether the set is empty or not.
     */
    public boolean isEmpty() {
        return bitmask == 0;
    }
    
    /**
     * Checks if the given value exists in this set.
     * @param value The value to look for.
     * @return Whether the value exists or not.
     */
    public boolean contains(int value) {
        return (bitmask & (1 << value)) != 0;
    }
    
    /**
     * Performs an action on each element of this set.
     * @param action The action to execute.
     */
    public void forEach(IntConsumer action) {
        for (int i = 0; i <= 64; i++) {
            if ((bitmask & (1 << i)) != 0)
                action.accept(i);
        }
    }
    
    /**
     * Creates a stream of all the values in this set.
     * @return The streamed values.
     */
    public IntStream stream() {
        return IntStream.range(0, 65).filter(i -> (bitmask & (1 << i)) != 0);
    }

}
