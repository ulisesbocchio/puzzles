package com.ulisesbocchio.github.puzzles.matrixpath;

import com.ulisesbocchio.github.puzzles.dfsbfsmap.DfsBfsWithMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ulises Bocchio
 */
public class MatrixPathGraph {
    public static boolean pathExists(int[][] graph) {
        int n = graph.length + 1;
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        for (int v = 0; v < n - 1; v++) {
            for (int h = 0; h < n; h++) {
                int node = graph[v][h];
                int nodeIndex = (v * n) + h;
                List<Integer> adjacents = new LinkedList<>();
                if (h > 0) {
                    int left = graph[v][h - 1];
                    if (node != left) {
                        adjacents.add(nodeIndex - 1);
                    }
                }
                if (h < n - 1) {
                    int right = graph[v][h + 1];
                    if (node != right) {
                        adjacents.add(nodeIndex + 1);
                    }
                }
                if (v > 0) {
                    int up = graph[v - 1][h];
                    if (node != up) {
                        adjacents.add(nodeIndex - n);
                    }
                }
                if (v < n - 2) {
                    int down = graph[v + 1][h];
                    if (node != down) {
                        adjacents.add(nodeIndex + n);
                    }
                }
                graphMap.put(nodeIndex, adjacents);
            }

        }
        return DfsBfsWithMap.pathExistsDFS(graphMap, 0, (n * (n - 1)) - 1);
    }
}
