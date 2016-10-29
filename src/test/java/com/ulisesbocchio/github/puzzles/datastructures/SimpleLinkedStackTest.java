package com.ulisesbocchio.github.puzzles.datastructures;

/**
 * @author Ulises Bocchio
 */
public class SimpleLinkedStackTest extends StackTest {

    @Override
    <T> Stack<T> newStack(int capacity, Class<T> clazz) {
        return new SimpleLinkedStack<>();
    }

    @Override
    boolean supportsLimitedCapacity() {
        return false;
    }
}