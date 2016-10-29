package com.ulisesbocchio.github.puzzles.datastructures;

import com.google.common.base.Preconditions;

import java.util.NoSuchElementException;

/**
 * @author Ulises Bocchio
 */
public class SimpleArrayQueue<T> implements Queue<T> {

    private int count;
    private T[] queue;
    private int capacity;

    @SuppressWarnings("unchecked")
    public SimpleArrayQueue(int capacity) {
        Preconditions.checkArgument(capacity >= 0, "Capacity cannot be negative");
        this.capacity = capacity;
        this.count = 0;
        this.queue = (T[]) new Object[capacity > 0 ? capacity : 1];
    }

    public SimpleArrayQueue() {
        this(0);
    }

    @Override
    public void add(T e) {
        checkCapacity();
        addInternal(e);
    }

    private void addInternal(T e) {
        maybeResize();
        queue[count++] = e;
    }

    private void checkCapacity() {
        Preconditions.checkState(hasCapacity());
    }

    private boolean hasCapacity() {
        return capacity == 0 || count < capacity;
    }

    @SuppressWarnings("unchecked")
    private void maybeResize() {
        if (count == queue.length) {
            T[] copy = (T[]) new Object[2 * queue.length];
            for (int i = 0; i < queue.length; i++) {
                copy[i] = queue[i];
            }
            queue = copy;
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return shiftLeft();
    }

    private T shiftLeft() {
        T elem = queue[0];
        for (int i = 0; i < count - 1; i++) {
            queue[i] = queue[i + 1];
        }
        count--;
        return elem;
    }

    @Override
    public T peek() {
        return isEmpty() ? null : queue[0];
    }

    @Override
    public boolean offer(T e) {
        if(hasCapacity()) {
            addInternal(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }
}
