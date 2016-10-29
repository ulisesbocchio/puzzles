package com.ulisesbocchio.github.puzzles.datastructures;

import com.google.common.base.Preconditions;

import java.util.NoSuchElementException;

/**
 * @author Ulises Bocchio
 */
public class SimpleLinkedQueue<T> implements Queue<T> {

    private int count = 0;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        T elem;
        Node<T> next;

        Node(T elem) {
            this.elem = elem;
        }
    }

    @Override
    public void add(T e) {
        Preconditions.checkArgument(e != null, "Element cannot be null");
        Node<T> node = new Node<>(e);
        if(first != null) {
            last.next = node;
            last = node;
        } else {
            first = last = node;
        }
        count++;
    }

    @Override
    public T remove() {
        if(isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
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
        add(e);
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
