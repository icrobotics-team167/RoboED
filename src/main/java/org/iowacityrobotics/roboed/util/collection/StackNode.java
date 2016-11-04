package org.iowacityrobotics.roboed.util.collection;

/**
 * Represents a single element in a linked stack. Useful for keeping track of an element and all its parents.
 * @author Evan Geng
 */
public class StackNode<T> {

    /**
     * The value held by this stack node.
     */
    private T value;
    
    /**
     * This stack node's parent.
     */
    private StackNode<T> parent;
    
    /**
     * Creates a new stack node with the given value and parent node.
     * @param value The new node's value.
     * @param parent The node's parent node.
     */
    public StackNode(T value, StackNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }
    
    /**
     * Creates a new stack node with the given value and no parent.
     * @param value The new node's value.
     */
    public StackNode(T value) {
        this(value, null);
    }
    
    /**
     * Creates a new stack node with no value and no parent.
     */
    public StackNode() {
        this(null);
    }
    
    /**
     * Gets this stack node's parent node.
     * @return The parent node, if it exists.
     */
    public StackNode<T> getParent() {
        return parent;
    }
    
    /**
     * Sets this stack node's parent node.
     * @param parent The new parent.
     */
    public void setParent(StackNode<T> parent) {
        this.parent = parent;
    }
    
    /**
     * Checks if this stack node has a parent node.
     * @return Whether this node has a parent or not.
     */
    public boolean hasParent() {
        return this.parent != null;
    }
    
    /**
     * Gets the value held by this node.
     * @return The node's value.
     */
    public T getValue() {
        return value;
    }
    
    /**
     * Sets the value held by this node.
     * @param value The new value.
     */
    public void setValue(T value) {
        this.value = value;
    }
    
    /**
     * Creates a new child node of this stack node.
     * @param value The child node's value.
     * @return The newly-created child node.
     */
    public StackNode<T> extend(T value) {
        return new StackNode<>(value, this);
    }
    
    /**
     * Creates a new child node of this stack node with a null value.
     * @return The newly-created child node.
     */
    public StackNode<T> extend() {
        return extend(null);
    }
    
}
