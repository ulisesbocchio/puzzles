package com.ulisesbocchio.github.puzzles.datastructures;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Ulises Bocchio
 */
public class WrapperStack<T> implements Stack<T> {

    private Deque<T> stack = new LinkedList<>();

    @Override
    public void push(T e) {
        stack.push(e);
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public T peek() {
        return stack.peek();
    }

    @Override
    public boolean offer(T e) {
        return stack.offerFirst(e);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
