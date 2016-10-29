# puzzles
Programming Puzzles

## 1. [Jelly Bean Jar](./src/main/java/com/ulisesbocchio/github/puzzles/jellybean)

There is a Jar of jelly beans on which each jelly bean color/flavor has certain probability to be picked when drawing a
jelly bean from the Jar.

## 2. [DFS & BFS with map representation](./src/main/java/com/ulisesbocchio/github/puzzles/dfsbfsmap)

A Depth First Search and Breadth First Search implementation where the graph is represented with a 
`Map<Integer, List<Integer>>`. The algorithm implemented is `pathExists` where true is returned if a path exists between 
2 provided nodes.

## 3. [DFS with Matrix representation](./src/main/java/com/ulisesbocchio/github/puzzles/matrixpath)

A Matrix representation of a directed Graph that is converted to the map representation from above to evaluate if a path
exists from the top-left corner to the bottom-right corner of the matrix.

## 4. [Compound Words](./src/main/java/com/ulisesbocchio/github/puzzles/compound)

Find out whether a given word is compound or not given a dictionary of words.
A compound word by definition is a new word form by the juxtaposition of 2 other words

## 5. [Missing Number](./src/main/java/com/ulisesbocchio/github/puzzles/missingnumber)

Given an array that contains all natural numbers from `1` to `n` except for one, find that missing number.

## 6. [Rate Limiter](./src/main/java/com/ulisesbocchio/github/puzzles/ratelimiter)

A class tha implements an `acquire():boolean` method that returns `true` when a rate slot is
available for use and false otherwise. The class instance is initialized with a `rate` or `QPS`, "queries per second"
that the rate limiter can accept (return `true`) every second.

## 7. [Data Structures](./src/main/java/com/ulisesbocchio/github/puzzles/datastructures)

* Stack
* Queue