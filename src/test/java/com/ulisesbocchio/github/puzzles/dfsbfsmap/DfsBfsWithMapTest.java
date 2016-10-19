package com.ulisesbocchio.github.puzzles.dfsbfsmap;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Ulises Bocchio
 */
public class DfsBfsWithMapTest {

    private Map<Integer, List<Integer>> graph;

    @Before
    public void init() {
        graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(3, 4));
        graph.put(3, Collections.singletonList(5));
        graph.put(4, Collections.singletonList(5));
        graph.put(5, Collections.emptyList());
    }

    @Test
    public void testDFS() throws Exception {
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFS(graph, 1, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFS(graph, 3, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFS(graph, 2, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFS(graph, 1, 10)).isFalse();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFS(graph, 2, 1)).isFalse();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFS(graph, 10, 1)).isFalse();
    }

    @Test
    public void testDFSIterative() throws Exception {
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFSIterative(graph, 1, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFSIterative(graph, 3, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFSIterative(graph, 2, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFSIterative(graph, 1, 10)).isFalse();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFSIterative(graph, 2, 1)).isFalse();
        Assertions.assertThat(DfsBfsWithMap.pathExistsDFSIterative(graph, 10, 1)).isFalse();
    }

    @Test
    public void testBFS() throws Exception {
        Assertions.assertThat(DfsBfsWithMap.pathExistsBFS(graph, 1, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsBFS(graph, 3, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsBFS(graph, 2, 5)).isTrue();
        Assertions.assertThat(DfsBfsWithMap.pathExistsBFS(graph, 1, 10)).isFalse();
        Assertions.assertThat(DfsBfsWithMap.pathExistsBFS(graph, 2, 1)).isFalse();
        Assertions.assertThat(DfsBfsWithMap.pathExistsBFS(graph, 10, 1)).isFalse();
    }

}