# Path Exists in Matrix Representation of Graph

## Problem
Given the DFS implementation on [DfsBfsWithMap](../dfsbfsmap/DfsBfsWithMap.java) and a Matrix `n` by `n-1` that
contains only `0`'s and `1`'s where different values between adjacent positions (up, down, left, right) indicates an
arrow in the graph from a given (x, y) position to the neighbor position ((x-1, y), (x+1, y), (x, y-1), (x, y+1)). 
Implement a `pathExists` method that given such matrix, it converts the matrix representation into the Map
representation used by [DfsBfsWithMap](../dfsbfsmap/DfsBfsWithMap.java) and evaluates whether there's a path from the
top-left corner (0, 0) to the bottom-right corner (n-2, n-1);

## Solution

The solution consists on going through each of the elements of the matrix, and on each position, evaluate whether each
neighbor (up, dow, left, right) has a different value than the current position, and if so, add it to the list of
adjacents nodes. Diagonal neighbors are not considered. The node number is calculated starting from `0` to 
`((n * (n - 1)) - 1)`;

### To illustrate the solution think about this scenario:

```java
int[][] graph = {
                {0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 1, 0, 1}
        };
        
        // Graph:
        // {0=[1],
        //  1=[0, 5],
        //  2=[6],
        //  3=[],
        //  4=[],
        //  5=[1, 9],
        //  6=[7, 2],
        //  7=[6],
        //  8=[9],
        //  9=[8, 10, 5],
        //  10=[9, 11],
        //  11=[10]}
```

The matrix represents the commented Graph. Where `n = 4` and there's a path from (0, 0) to (2, 3) which is:

(0,0), (0,1), (1,1), (2,1), (2, 2), (2, 3) since at each position we have different values: (0,1,0,1,0,1).
In the Map representation each matrix position was replaced with a number from `0` to `11`, so the path be be found is
 from node `0` to `11` (`(n * (n - 1)) - 1 = 4 * 3 - 1`).
 
 ## Test
 
 Test is in class [MatrixPathGraphTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/matrixpath/MatrixPathGraphTest.java)

## Time Complexity

Worst case: `O(n^2)`

Technically n * (n-1), the elements of the matrix.

## Space Complexity

`O(n^2)`

Since we have to store the matrix into the map.