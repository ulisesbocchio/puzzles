# Find the missing number in a Array

## Problem
Given an array that contains all natural numbers from `1` to `n` except for one, find that missing number.
Notice the array does not have the numbers in order, and the capacity of the array is `n-1`.

## Solution

The optimal solution consists on using the Zigma formula for natural numbers: `n*(n+1)/2` to calculate the
sum of all numbers from `1` to `n`, sum all the numbers in the array, and the difference
of the two numbers is the one that's missing.

The are other two solutions for comparison. The linear solution is similar to the optimal in logic but it uses
a linear operation to calculate the Sum of numbers from `1` to `n` instead of the formula.

The worse case solution does not operate this way, instead it sorts the array, and then compares the value of each
element with its position + 1, since in an ordered array with numbers from `1` to `N` the rule `array[n-1] = n` applies
for any given `n`, until it finds the one that does not follow the rule.

### To illustrate the solution think about this scenario:

```java
int[] numbers = {4, 2, 1, 5};
```
Where N = 5 and the missing number is 3 <br/>
Sum of numbers up to 5 =\> n * (n + 1) / 2 = 5 * (5 + 1) / 2 = 15 <br/>
Sum of numbers in the array =\> 4 + 2 + 1 + 5 = 12 <br>
Missing number =\> 15 - 12 = 3

## Test

 Test is in class [MissingNumberTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/missingnumber/MissingNumberTest.java)

## Time Complexity

Worst case:
 - Optimal: `O(n)`
 - Linear: `O(n)` (But does 2 x N since it calculates the sum through iteration)
 - Worse: `O(n log n)` since depends on sort

## Space Complexity

`O(1)`