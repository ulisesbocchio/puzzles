package com.ulisesbocchio.github.puzzles.datastructures;

/**
 * @author Ulises Bocchio
 */
public class WrapperQueueTest extends QueueTest {

    @Override
    <T> Queue<T> newQueue(int capacity, Class<T> clazz) {
        return new WrapperQueue<>();
    }

    @Override
    boolean supportsLimitedCapacity() {
        return false;
    }
}