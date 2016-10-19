package com.ulisesbocchio.github.puzzles.dfsbfsmap;

import java.util.*;

/**
 * @author Ulises Bocchio
 */
public class DfsBfsWithMap {
    static boolean pathExistsDFS(Map<Integer, List<Integer>> graph, int a, int b) {
        return pathExistsDFSInternal(graph, new HashSet<>(), a, b);
    }

    static boolean pathExistsDFSInternal(Map<Integer, List<Integer>> graph, Set<Integer> visited, int a, int b) {
        boolean found = false;
        if (a == b) {
            found = true;
        } else if (!visited.contains(a) && graph.containsKey(a)) {
            List<Integer> adjacents = graph.get(a);
            Iterator<Integer> it = adjacents.iterator();
            while (it.hasNext() && !found) {
                found = pathExistsDFSInternal(graph, visited, it.next(), b);
            }
        }
        visited.add(a);
        return found;
    }

    static boolean pathExistsDFSIterative(Map<Integer, List<Integer>> graph, int a, int b) {
        LinkedList<Integer> stack = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(a);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (node == b) {
                return true;
            } else if (!visited.contains(node)) {
                Optional.ofNullable(graph.get(node))
                        .ifPresent(adj -> adj.forEach(stack::push));
                visited.add(node);
            }

        }
        return false;
    }

    static boolean pathExistsBFS(Map<Integer, List<Integer>> graph, int a, int b) {
        LinkedList<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(a);
        while (!queue.isEmpty()) {
            int node = queue.remove();
            if (node == b) {
                return true;
            } else if (!visited.contains(node)) {
                Optional.ofNullable(graph.get(node))
                        .ifPresent(queue::addAll);
                visited.add(node);
            }

        }
        return false;
    }
}
