package org.iowacityrobotics.roboed.util.primitive;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A map that uses the primitive <code>int</code> for keys. Implemented in a similar fashion to a hash table, except uses the integer keys themselves to determine the appropriate bucket.
 * @param <T> The value type.
 * @author Evan Geng
 */
public class IntTMap<T> {

    /**
     * The buckets of bindings.
     */
    private Node[] buckets;

    /**
     * The number of bindings to wait for before expanding the map.
     */
    private int threshold;

    /**
     * The number of bindings in the map.
     */
    private int size;

    /**
     * Creates a new IntTMap with 16 buckets and a load factor of 75%.
     */
    public IntTMap() {
        this(16);
    }

    /**
     * Creates a new IntTMap with the given initial number of buckets and a load factor of 75%.
     * @param buckets The number of buckets to use.
     */
    public IntTMap(int buckets) {
        this(buckets, 0.75F);
    }

    /**
     * Creates a new IntTMap with the given initial bucket count and load factor.
     * @param buckets The number of buckets to use.
     * @param loadFactor The load factor.
     */
    public IntTMap(int buckets, float loadFactor) {
        this.buckets = new Node[buckets];
        this.threshold = (int)Math.ceil(loadFactor * buckets);
        this.size = 0;
    }

    /**
     * Creates a new map entry with the given key and value.
     * @param key The entry's key.
     * @param value The entry's value.
     */
    public void put(int key, T value) {
        if (value == null)
            throw new NullPointerException();
        if (++size >= threshold)
            resize(buckets.length * 2);
        Node node = buckets[key % buckets.length];
        if (node == null)
            buckets[key % buckets.length] = new Node(key, value);
        else {
            while (node.next != null)
                node = node.next;
            node.next = new Node(key, value);
        }
    }

    /**
     * Gets the value mapped to the given key.
     * @param key The key to look up.
     * @return The value found, if one exists. Otherwise, returns null.
     */
    @SuppressWarnings("unchecked")
    public T get(int key) {
        Node node = buckets[key & buckets.length];
        if (node == null)
            return null;
        while (node != null) {
            if (node.key == key)
                return (T)node.value;
            node = node.next;
        }
        return null;
    }

    /**
     * Removes the mapping for a given value.
     * @param key The key to remove.
     * @return The value previously mapped to the key, if one existed. Otherwise, returns null.
     */
    @SuppressWarnings("unchecked")
    public T remove(int key) {
        Node node = buckets[key % buckets.length];
        if (node == null)
            return null;
        else {
            Node prev = null;
            while (node != null) {
                if (node.key == key) {
                    T val = (T)node.value;
                    if (prev != null)
                        prev.next = node.next;
                    else
                        buckets[key % buckets.length] = node.next;
                    return val;
                }
                prev = node;
                node = node.next;
            }
            return null;
        }
    }

    /**
     * Removes all mappings.
     */
    public void clear() {
        for (int i = 0; i < buckets.length; i++)
            buckets[i] = null;
        size = 0;
    }

    /**
     * Gets the number of entries in this map.
     * @return The entry count.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if this map contains no entries.
     * @return Whether this map is empty or not.
     */
    public boolean isEmpty() {
        return size > 0;
    }

    /**
     * Checks if this map contains the given key.
     * @param key The key to look up.
     * @return Whether an entry exists for the given key or not.
     */
    public boolean contains(int key) {
        return get(key) != null;
    }

    /**
     * Iterates through all entries in this map.
     * @param action The action to perform on each entry.
     */
    @SuppressWarnings("unchecked")
    public void forEach(IIntTBiConsumer<T> action) {
        for (Node n : buckets) {
            while (n != null) {
                action.accept(n.key, (T)n.value);
                n = n.next;
            }
        }
    }

    /**
     * If the key exists, returns the value it's mapped to. Otherwise, uses the factory to create a new entry.
     * @param key The key.
     * @param mapper The factory function for new values.
     * @return The entry mapped to the key, whether it was created or not.
     */
    public T computeIfAbsent(int key, IntFunction<T> mapper) {
        T value = get(key);
        if (value == null) {
            value = mapper.apply(key);
            put(key, value);
        }
        return value;
    }

    /**
     * Streams all entries in this map, represented as {@link IntTPair}s.
     * @return The stream of entries.
     */
    @SuppressWarnings("unchecked")
    public Stream<IntTPair<T>> stream() {
        Collection<IntTPair<T>> bindings = new LinkedList<>();
        for (Node n : buckets) {
            while (n != null) {
                bindings.add(IntTPair.of(n.key, (T)n.value));
                n = n.next;
            }
        }
        return bindings.stream();
    }

    /**
     * Changes the number of buckets used by this map.
     * @param bucketCount The new bucket count.
     */
    @SuppressWarnings("unchecked")
    private void resize(int bucketCount) {
        if (bucketCount == buckets.length)
            return;
        Node[] prev = buckets;
        buckets = new Node[bucketCount];
        for (Node n : prev) {
            while (n != null) {
                put(n.key, (T)n.value);
                n = n.next;
            }
        }
    }

    /**
     * Represents a single map entry.
     */
    private static class Node {

        /**
         * The key for this entry.
         */
        int key;

        /**
         * The value for this entry,
         */
        Object value;

        /**
         * The next entry in this bucket, if it exists.
         */
        Node next;

        /**
         * Creates a new entry from the given key and value.
         * @param key The entry's key
         * @param value The entry's value
         */
        Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

    }

}
