package com.ulisesbocchio.github.puzzles.datastructures;

/**
 * @author Ulises Bocchio
 */
public class SimpleLinkedQueueTest extends QueueTest {

    @Override
    <T> Queue<T> newQueue(int capacity, Class<T> clazz) {
        return new SimpleLinkedQueue<>();
    }

    @Override
    boolean supportsLimitedCapacity() {
        return false;
    }
}