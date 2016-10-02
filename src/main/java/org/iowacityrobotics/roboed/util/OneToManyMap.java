package org.iowacityrobotics.roboed.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A map that binds a single key to multiple different values.
 * @param <K> The type of a key.
 * @param <V> The type of a value.
 * @param <C> The collection to use to store values.
 * @author Evan Geng
 */
public class OneToManyMap<K, V, C extends Collection<V>> {

    /**
     * The backing {@link HashMap} that stores the value collections.
     */
    private final Map<K, C> backing;

    /**
     * The factory function for creating value collections.
     */
    private final Supplier<C> factory;

    /**
     * Creates a new OneToManyMap using the given factory function for value collection creation.
     * @param factory The factory function for value collections.
     */
    public OneToManyMap(Supplier<C> factory) {
        backing = new HashMap<>();
        this.factory = factory;
    }

    /**
     * Inserts a new entry into this map.
     * @param key The entry's key.
     * @param val The entry's value.
     */
    public void put(K key, V val) {
        C vals = backing.get(key);
        if (vals == null) {
            vals = factory.get();
            backing.put(key, vals);
        }
        vals.add(val);
    }

    /**
     * Gets a collection of values mapped to the given key.
     * @param key The key to look up.
     * @return All values mapped to the given key.
     */
    public C get(K key) {
        return backing.get(key);
    }

    /**
     * Removes a particular key-value mapping.
     * @param key The key to remove.
     * @param val The value to remove.
     * @return Whether the operation was successful or not.
     */
    public boolean remove(K key, V val) {
        C vals = backing.get(key);
        if (vals != null) {
            boolean success = vals.remove(val);
            if (vals.isEmpty())
                backing.remove(key);
            return success;
        }
        return false;
    }

    /**
     * Removes all values mapped to a given key.
     * @param key The key to remove.
     * @return Whether the operation was successful or not.
     */
    public boolean clear(K key) {
        return backing.remove(key) != null;
    }

    /**
     * Removes all mappings.
     */
    public void clear() {
        backing.clear();
    }

    /**
     * Gets the number of keys in this map.
     * @return The key count.
     */
    public int size() {
        return backing.size();
    }

    /**
     * Checks whether this map is empty or not.
     * @return Whether this map is empty or not.
     */
    public boolean isEmpty() {
        return backing.isEmpty();
    }

    /**
     * Checks if the given key exists.
     * @param key The key to look up.
     * @return Whether the key exists or not.
     */
    public boolean contains(K key) {
        return backing.containsKey(key);
    }

    /**
     * Checks if the given mapping exists.
     * @param key The key to look up.
     * @param val The value to expect.
     * @return Whether the key-value mapping exists or not.
     */
    public boolean contains(K key, V val) {
        C vals = backing.get(key);
        return vals != null && vals.contains(val);
    }

    /**
     * Iterates through all mappings.
     * @param action The action to perform on each mapping.
     */
    public void forEach(BiConsumer<K, V> action) {
        backing.forEach((k, c) -> c.forEach(v -> action.accept(k, v)));
    }

    /**
     * Creates a stream of all mappings, represented as {@link Pair}s.
     * @return The streamed entries.
     */
    public Stream<Pair<K, V>> stream() {
        return backing.entrySet().stream().flatMap(e -> e.getValue().stream().map(v -> Pair.of(e.getKey(), v)));
    }

}