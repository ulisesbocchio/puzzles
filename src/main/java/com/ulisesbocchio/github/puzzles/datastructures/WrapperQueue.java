package com.ulisesbocchio.github.puzzles.datastructures;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Ulises Bocchio
 */
public class WrapperQueue<T> implements Queue<T> {

    private Deque<T> queue = new LinkedList<>();

    @Override
    public void add(T e) {
        queue.add(e);
    }

    @Override
    public T remove() {
        return queue.remove();
    }

    @Override
    public T peek() {
        return queue.peek();
    }

    @Override
    public boolean offer(T e) {
        return queue.offer(e);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }
}
