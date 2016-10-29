package com.ulisesbocchio.github.puzzles.datastructures;

import com.google.common.base.Preconditions;

import java.util.NoSuchElementException;

/**
 * @author Ulises Bocchio
 */
public class SimpleLinkedStack<T> implements Stack<T> {

    private int count = 0;
    private Node<T> first = null;

    private static class Node<T> {
        T elem;
        Node<T> next;

        Node(T elem) {
            this.elem = elem;
        }
    }

    @Override
    public void push(T e) {
        Preconditions.checkArgument(e != null, "Element cannot be null");
        Node<T> node = new Node<>(e);
        node.next = first;
        count++;
        first = node;
    }

    @Override
    public T pop() {
        if(isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T head = first.elem;
        first = first.next;
        count--;
        return head;
    }

    @Override
    public T peek() {
        return isEmpty() ? null : first.elem;
    }

    @Override
    public boolean offer(T e) {
        push(e);
        return true;
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
