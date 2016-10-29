package com.ulisesbocchio.github.puzzles.datastructures;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * @author Ulises Bocchio
 */
public class SimpleArrayStackTest extends StackTest {

    @Override
    <T> Stack<T> newStack(int capacity, Class<T> clazz) {
        return new SimpleArrayStack<>(capacity);
    }

    @Override
    boolean supportsLimitedCapacity() {
        return true;
    }
}