package com.ulisesbocchio.github.puzzles.datastructures;

import com.google.common.base.Preconditions;

import java.util.NoSuchElementException;

/**
 * @author Ulises Bocchio
 */
public class SimpleArrayStack<T> implements Stack<T> {

    private int count;
    private T[] stack;
    private int capacity;

    @SuppressWarnings("unchecked")
    public SimpleArrayStack(int capacity) {
        Preconditions.checkArgument(capacity >= 0, "Capacity cannot be negative");
        this.capacity = capacity;
        this.count = 0;
        this.stack = (T[]) new Object[capacity > 0 ? capacity : 1];
    }

    public SimpleArrayStack() {
        this(0);
    }

    private void checkCapacity() {
        Preconditions.checkState(hasCapacity());
    }

    private boolean hasCapacity() {
        return capacity == 0 || count < capacity;
    }

    @SuppressWarnings("unchecked")
    private void maybeResize() {
        if (count == stack.length) {
            T[] copy = (T[]) new Object[2 * stack.length];
            for (int i = 0; i < stack.length; i++) {
                copy[i] = stack[i];
            }
            stack = copy;
        }
    }

    private T shiftLeft() {
        T elem = stack[0];
        for (int i = 0; i < count - 1; i++) {
            stack[i] = stack[i + 1];
        }
        count--;
        return elem;
    }

    private void shiftRight() {
        for (int i = count; i > 0; i--) {
            stack[i] = stack[i - 1];
        }
        count++;
    }

    @Override
    public void push(T e) {
        checkCapacity();
        pushInternal(e);
    }

    private void pushInternal(T e) {
        maybeResize();
        shiftRight();
        stack[0] = e;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return shiftLeft();
    }

    @Override
    public T peek() {
        return isEmpty() ? null : stack[0];
    }

    @Override
    public boolean offer(T e) {
        if(hasCapacity()) {
            pushInternal(e);
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
