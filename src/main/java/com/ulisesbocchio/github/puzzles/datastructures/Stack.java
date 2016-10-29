package com.ulisesbocchio.github.puzzles.datastructures;

import java.util.NoSuchElementException;

/**
 * A LIFO (Last in, First out) collection contract.
 *
 * @author Ulises Bocchio
 */
public interface Stack<T> {
    /**
     * Adds an element to the beginning of the stack.
     *
     * @param e The element to add.
     * @throws IllegalStateException in case of space limitations.
     */
    void push(T e);
    /**
     * Returns and removes the first element of the stack.
     *
     * @return the first element of the stack
     * @throws NoSuchElementException if stack is empty.
     */
    T pop();
    /**
     * Returns if exists the first element of the stack without removing it.
     *
     * @return the first element of the stack.
     */
    T peek();

    /**
     * Adds an element to the beginning of the stack if possible. Returns true if the element
     * was added, false otherwise.
     *
     * @param e the element to add.
     * @return true if the element was added to the stack, false otherwise.
     */
    boolean offer(T e);

    /**
     * Returns true if the stack is empty, false otherwise.
     *
     * @return true if the stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     * @return the number of elements in the stack.
     */
    int size();
}
