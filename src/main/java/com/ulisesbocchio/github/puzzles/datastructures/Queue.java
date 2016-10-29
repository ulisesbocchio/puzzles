package com.ulisesbocchio.github.puzzles.datastructures;

import java.util.NoSuchElementException;

/**
 * A FIFO (First in, First out) collection contract.
 *
 * @author Ulises Bocchio
 */
public interface Queue<T> {
    /**
     * Adds an element to the end of the queue.
     *
     * @param e The element to add.
     * @throws IllegalStateException in case of space limitations.
     */
    void add(T e);

    /**
     * Returns and removes the first element of the queue.
     *
     * @return the first element of the queue
     * @throws NoSuchElementException if queue is empty.
     */
    T remove();

    /**
     * Returns if exists the first element of the queue without removing it.
     *
     * @return the first element of the queue.
     */
    T peek();

    /**
     * Adds an element to the end of the queue if possible. Returns true if the element
     * was added, false otherwise.
     *
     * @param e the element to add.
     * @return true if the element was added to the queue, false otherwise.
     */
    boolean offer(T e);

    /**
     * Returns true if the queue is empty, false otherwise.
     *
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     * @return the number of elements in the queue.
     */
    int size();

}
