package com.ulisesbocchio.github.puzzles.datastructures;

/**
 * @author Ulises Bocchio
 */
public class WrapperStackTest extends StackTest {

    @Override
    <T> Stack<T> newStack(int capacity, Class<T> clazz) {
        return new WrapperStack<>();
    }

    @Override
    boolean supportsLimitedCapacity() {
        return false;
    }
}