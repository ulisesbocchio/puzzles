# DFS, BFS with `Map<Integer, List<Integer>>` representation

`pathExists(a,b):boolean` implementation using:

* Iterative and Recursive Depth First Search
* Iterative Breadth First Search

Test is in class `DfsBfsWithMapTest`

## Graph Representation

The graph is represented on a Map, each key is an Edge and the value of each Edge are the Vertices. For instance:

```java
Map<Integer, List<Integer>> graph = new HashMap<>();
graph.put(1, Arrays.asList(2, 3));
graph.put(2, Arrays.asList(3, 4));
graph.put(3, Collections.singletonList(5));
graph.put(4, Collections.singletonList(5));
graph.put(5, Collections.emptyList());
```

Is a map with 5 edges, on which each edge specifies its adjacents edges or vertices.

## Time Complexity
O(E + V)

Being E the number of Edges and V the number of Vertices.