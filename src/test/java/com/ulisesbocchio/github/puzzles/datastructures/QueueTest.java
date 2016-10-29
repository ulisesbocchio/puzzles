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
public abstract class QueueTest {
    abstract <T> Queue<T> newQueue(int capacity, Class<T> clazz);
    abstract boolean supportsLimitedCapacity();


    @Test(expected = IllegalArgumentException.class)
    public void createInvalid() {
        Assume.assumeTrue(supportsLimitedCapacity());

        newQueue(-1, Integer.class);
    }

    @Test
    public void addFail() {
        Assume.assumeTrue(supportsLimitedCapacity());

        Queue<Integer> queue = newQueue(10, Integer.class);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(7);
        queue.add(8);
        queue.add(9);
        queue.add(10);
        assertThat(catchThrowable(() -> queue.add(11)))
                .isInstanceOf(IllegalStateException.class);

        Queue<Integer> queue2 = newQueue(1, Integer.class);
        queue2.add(1);
        assertThat(catchThrowable(() -> queue2.add(2)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void add() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(7);
        queue.add(8);
        queue.add(9);
        queue.add(10);
        assertThat(queue.size()).isEqualTo(10);
        assertThat(queue.peek()).isEqualTo(1);
    }

    @Test
    public void remove() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        queue.add(1);
        assertThat(queue.remove()).isEqualTo(1);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(2);
        assertThat(queue.remove()).isEqualTo(2);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(3);
        assertThat(queue.remove()).isEqualTo(3);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(4);
        assertThat(queue.remove()).isEqualTo(4);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(5);
        assertThat(queue.remove()).isEqualTo(5);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(6);
        assertThat(queue.remove()).isEqualTo(6);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(7);
        assertThat(queue.remove()).isEqualTo(7);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(8);
        assertThat(queue.remove()).isEqualTo(8);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(9);
        assertThat(queue.remove()).isEqualTo(9);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(10);
        assertThat(queue.remove()).isEqualTo(10);
        assertThat(queue.size()).isEqualTo(0);

        assertThat(queue.isEmpty()).isEqualTo(true);
    }

    @Test
    public void remove2() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(7);
        queue.add(8);
        queue.add(9);
        queue.add(10);
        assertThat(queue.remove()).isEqualTo(1);
        assertThat(queue.remove()).isEqualTo(2);
        assertThat(queue.remove()).isEqualTo(3);
        assertThat(queue.remove()).isEqualTo(4);
        assertThat(queue.remove()).isEqualTo(5);
        assertThat(queue.remove()).isEqualTo(6);
        assertThat(queue.remove()).isEqualTo(7);
        assertThat(queue.remove()).isEqualTo(8);
        assertThat(queue.remove()).isEqualTo(9);
        assertThat(queue.remove()).isEqualTo(10);

        assertThat(queue.isEmpty()).isEqualTo(true);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFail() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        queue.remove();
    }

    @Test
    public void removeFail2() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        IntStream.rangeClosed(1, 5).forEach(queue::add);
        IntStream.rangeClosed(1, 5).forEach(n -> queue.remove());
        assertThat(catchThrowable(queue::remove))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void peek() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        assertThat(queue.peek()).isNull();
        queue.add(1);
        assertThat(queue.peek()).isEqualTo(1);
        queue.add(2);
        assertThat(queue.peek()).isEqualTo(1);
        queue.remove();
        queue.remove();
        assertThat(queue.peek()).isNull();
    }

    @Test
    public void offer() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        assertThat(queue.offer(1)).isTrue();
        assertThat(queue.offer(2)).isTrue();
        assertThat(queue.offer(3)).isTrue();
        assertThat(queue.offer(4)).isTrue();
        assertThat(queue.offer(5)).isTrue();
        assertThat(queue.offer(6)).isTrue();
        assertThat(queue.offer(7)).isTrue();
        assertThat(queue.offer(8)).isTrue();
        assertThat(queue.offer(9)).isTrue();
        assertThat(queue.offer(10)).isTrue();
        assertThat(queue.size()).isEqualTo(10);
        assertThat(queue.peek()).isEqualTo(1);
    }

    @Test
    public void offerFail() throws Exception {
        Assume.assumeTrue(supportsLimitedCapacity());

        Queue<Integer> queue = newQueue(1, Integer.class);
        assertThat(queue.offer(1)).isTrue();
        assertThat(queue.offer(2)).isFalse();
        assertThat(queue.size()).isEqualTo(1);
    }

    @Test
    public void isEmpty() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        assertThat(queue.isEmpty()).isTrue();
        queue.add(1);
        assertThat(queue.isEmpty()).isFalse();
        queue.remove();
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    public void size() throws Exception {
        Queue<Integer> queue = newQueue(0, Integer.class);
        assertThat(queue.size()).isEqualTo(0);
        queue.add(1);
        assertThat(queue.size()).isEqualTo(1);
        queue.add(2);
        assertThat(queue.size()).isEqualTo(2);
        queue.add(3);
        assertThat(queue.size()).isEqualTo(3);
        queue.add(4);
        assertThat(queue.size()).isEqualTo(4);
        queue.add(5);
        assertThat(queue.size()).isEqualTo(5);
        queue.add(6);
        assertThat(queue.size()).isEqualTo(6);
        queue.add(7);
        assertThat(queue.size()).isEqualTo(7);
        queue.add(8);
        assertThat(queue.size()).isEqualTo(8);
        queue.add(9);
        assertThat(queue.size()).isEqualTo(9);
        queue.add(10);
        assertThat(queue.size()).isEqualTo(10);
        queue.remove();
        assertThat(queue.size()).isEqualTo(9);
        queue.remove();
        assertThat(queue.size()).isEqualTo(8);
        queue.remove();
        assertThat(queue.size()).isEqualTo(7);
        queue.remove();
        assertThat(queue.size()).isEqualTo(6);
        queue.remove();
        assertThat(queue.size()).isEqualTo(5);
        queue.remove();
        assertThat(queue.size()).isEqualTo(4);
        queue.remove();
        assertThat(queue.size()).isEqualTo(3);
        queue.remove();
        assertThat(queue.size()).isEqualTo(2);
        queue.remove();
        assertThat(queue.size()).isEqualTo(1);
        queue.remove();
        assertThat(queue.size()).isEqualTo(0);
    }

    @Test
    public void thousand() {
        Queue<Integer> queue = newQueue(0, Integer.class);
        for(int i = 1; i <= 1000; i++) {
            queue.add(i);
        }
        assertThat(queue.size()).isEqualTo(1000);
        for(int i = 1; i <= 1000; i++) {
            assertThat(queue.remove()).isEqualTo(i);
        }
        assertThat(queue.size()).isEqualTo(0);
    }
}
