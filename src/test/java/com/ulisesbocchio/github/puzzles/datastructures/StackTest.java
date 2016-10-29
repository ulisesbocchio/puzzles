package com.ulisesbocchio.github.puzzles.datastructures;

import org.junit.Assume;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * @author Ulises Bocchio
 */
public abstract class StackTest {
    abstract <T> Stack<T> newStack(int capacity, Class<T> clazz);
    abstract boolean supportsLimitedCapacity();

    @Test(expected = IllegalArgumentException.class)
    public void createInvalid() {
        Assume.assumeTrue(supportsLimitedCapacity());

        newStack(-1, Integer.class);
    }

    @Test
    public void addFail() {
        Assume.assumeTrue(supportsLimitedCapacity());

        Stack<Integer> stack = newStack(10, Integer.class);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        assertThat(catchThrowable(() -> stack.push(11)))
                .isInstanceOf(IllegalStateException.class);

        Stack<Integer> stack2 = newStack(1, Integer.class);
        stack2.push(1);
        assertThat(catchThrowable(() -> stack2.push(2)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void push() throws Exception {
        Stack<Integer> stack = newStack(0, Integer.class);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        assertThat(stack.size()).isEqualTo(10);
        assertThat(stack.peek()).isEqualTo(10);
    }

    @Test
    public void pop() throws Exception {
        Stack<Integer> stack = newStack(0, Integer.class);
        stack.push(1);
        assertThat(stack.pop()).isEqualTo(1);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(2);
        assertThat(stack.pop()).isEqualTo(2);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(3);
        assertThat(stack.pop()).isEqualTo(3);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(4);
        assertThat(stack.pop()).isEqualTo(4);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(5);
        assertThat(stack.pop()).isEqualTo(5);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(6);
        assertThat(stack.pop()).isEqualTo(6);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(7);
        assertThat(stack.pop()).isEqualTo(7);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(8);
        assertThat(stack.pop()).isEqualTo(8);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(9);
        assertThat(stack.pop()).isEqualTo(9);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(10);
        assertThat(stack.pop()).isEqualTo(10);
        assertThat(stack.size()).isEqualTo(0);

        assertThat(stack.isEmpty()).isEqualTo(true);
    }

    @Test
    public void pop2() throws Exception {
        Stack<Integer> stack = newStack(0, Integer.class);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        assertThat(stack.pop()).isEqualTo(10);
        assertThat(stack.pop()).isEqualTo(9);
        assertThat(stack.pop()).isEqualTo(8);
        assertThat(stack.pop()).isEqualTo(7);
        assertThat(stack.pop()).isEqualTo(6);
        assertThat(stack.pop()).isEqualTo(5);
        assertThat(stack.pop()).isEqualTo(4);
        assertThat(stack.pop()).isEqualTo(3);
        assertThat(stack.pop()).isEqualTo(2);
        assertThat(stack.pop()).isEqualTo(1);

        assertThat(stack.isEmpty()).isEqualTo(true);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFail() throws Exception {
        Stack<Integer> stack = newStack(0, Integer.class);
        stack.pop();
    }

    @Test
    public void removeFail2() throws Exception {
        Stack<Integer> stack = newStack(0, Integer.class);
        IntStream.rangeClosed(1, 5).forEach(stack::push);
        IntStream.rangeClosed(1, 5).forEach(n -> stack.pop());
        assertThat(catchThrowable(stack::pop))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void peek() throws Exception {
        Stack<Integer> stack = newStack(0, Integer.class);
        assertThat(stack.peek()).isNull();
        stack.push(1);
        assertThat(stack.peek()).isEqualTo(1);
        stack.push(2);
        assertThat(stack.peek()).isEqualTo(2);
        stack.pop();
        stack.pop();
        assertThat(stack.peek()).isNull();
    }

    @Test
    public void offer() throws Exception {
        Stack<Integer> stack = newStack(0, Integer.class);
        assertThat(stack.offer(1)).isTrue();
        assertThat(stack.offer(2)).isTrue();
        assertThat(stack.offer(3)).isTrue();
        assertThat(stack.offer(4)).isTrue();
        assertThat(stack.offer(5)).isTrue();
        assertThat(stack.offer(6)).isTrue();
        assertThat(stack.offer(7)).isTrue();
        assertThat(stack.offer(8)).isTrue();
        assertThat(stack.offer(9)).isTrue();
        assertThat(stack.offer(10)).isTrue();
        assertThat(stack.size()).isEqualTo(10);
        assertThat(stack.peek()).isEqualTo(10);
    }

    @Test
    public void offerFail() throws Exception {
        Assume.assumeTrue(supportsLimitedCapacity());
        Stack<Integer> stack = newStack(1, Integer.class);
        assertThat(stack.offer(1)).isTrue();
        assertThat(stack.offer(2)).isFalse();
        assertThat(stack.size()).isEqualTo(1);
    }

    @Test
    public void isEmpty() throws Exception {

        Stack<Integer> stack = newStack(0, Integer.class);
        assertThat(stack.isEmpty()).isTrue();
        stack.push(1);
        assertThat(stack.isEmpty()).isFalse();
        stack.pop();
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    public void size() throws Exception {
        Stack<Integer> stack = newStack(0, Integer.class);
        assertThat(stack.size()).isEqualTo(0);
        stack.push(1);
        assertThat(stack.size()).isEqualTo(1);
        stack.push(2);
        assertThat(stack.size()).isEqualTo(2);
        stack.push(3);
        assertThat(stack.size()).isEqualTo(3);
        stack.push(4);
        assertThat(stack.size()).isEqualTo(4);
        stack.push(5);
        assertThat(stack.size()).isEqualTo(5);
        stack.push(6);
        assertThat(stack.size()).isEqualTo(6);
        stack.push(7);
        assertThat(stack.size()).isEqualTo(7);
        stack.push(8);
        assertThat(stack.size()).isEqualTo(8);
        stack.push(9);
        assertThat(stack.size()).isEqualTo(9);
        stack.push(10);
        assertThat(stack.size()).isEqualTo(10);
        stack.pop();
        assertThat(stack.size()).isEqualTo(9);
        stack.pop();
        assertThat(stack.size()).isEqualTo(8);
        stack.pop();
        assertThat(stack.size()).isEqualTo(7);
        stack.pop();
        assertThat(stack.size()).isEqualTo(6);
        stack.pop();
        assertThat(stack.size()).isEqualTo(5);
        stack.pop();
        assertThat(stack.size()).isEqualTo(4);
        stack.pop();
        assertThat(stack.size()).isEqualTo(3);
        stack.pop();
        assertThat(stack.size()).isEqualTo(2);
        stack.pop();
        assertThat(stack.size()).isEqualTo(1);
        stack.pop();
        assertThat(stack.size()).isEqualTo(0);
    }

    @Test
    public void thousand() {
        Stack<Integer> stack = newStack(0, Integer.class);
        for(int i = 1; i <= 1000; i++) {
            stack.push(i);
        }
        assertThat(stack.size()).isEqualTo(1000);
        for(int i = 1000; i >= 1; i--) {
            assertThat(stack.pop()).isEqualTo(i);
        }
        assertThat(stack.size()).isEqualTo(0);
    }
}
