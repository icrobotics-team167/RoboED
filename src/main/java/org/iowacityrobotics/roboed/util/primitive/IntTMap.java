package org.iowacityrobotics.roboed.util.primitive;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

public class IntTMap<T> {

	private Node[] buckets;
	private int threshold, size;

	public IntTMap() {
		this(16);
	}

	public IntTMap(int buckets) {
		this(buckets, 0.75F);
	}

	public IntTMap(int buckets, float loadFactor) {
		this.buckets = new Node[buckets];
		this.threshold = (int)Math.ceil(loadFactor * buckets);
		this.size = 0;
	}

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
	
	public void clear() {
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size > 0;
	}
	
	public boolean contains(int key) {
		return get(key) != null;
	}

	@SuppressWarnings("unchecked")
	public void forEach(IIntTBiConsumer<T> action) {
		for (Node n : buckets) {
			while (n != null) {
				action.accept(n.key, (T)n.value);
				n = n.next;
			}
		}
	}

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

	@SuppressWarnings("unchecked")
	private void resize(int bucketCount) {
		Node[] prev = buckets;
		buckets = new Node[bucketCount];
		for (Node n : prev) {
			while (n != null) {
				put(n.key, (T)n.value);
				n = n.next;
			}
		}
	}

	private static class Node {

		int key;
		Object value;
		Node next;

		Node(int key, Object value) {
			this.key = key;
			this.value = value;
		}

	}
	
}
