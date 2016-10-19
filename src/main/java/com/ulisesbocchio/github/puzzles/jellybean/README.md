# Jelly Bean Jar

## Problem
There is a Jar of jelly beans on which each jelly bean color/flavor has certain probability to be picked when drawing a
jelly bean from the Jar.<br/>
Given a set of probabilities for each flavor, implement a `getBean` algorithm that will return a flavor based on the
probabilities provided.

## Solution

`JellyBeanJar` implements the logic and there's a test in `JellyBeanJarTest`. <br>
The solution consists on a class that accepts a list of `JellyBeanDef` on its constructor. Each `JellyBeanDef` contains
the `JellyBean` flavor/color and probability of it to be picked.
The `JellyBeanJar` checks that in fact all definitions sum is `1` and sorts the probabilities in ascending order.<br/>
The `JellyBeanDef` checks that the given probability is `> 0`.
The main logic is in the `JellyBeanJar.getBean` method and consists of:

 * Select a random number in the range `[0, 1)`
 * Iterate over the list of probabilities in order, accumulating the sum of them in `cumulativeChance`
 * Once the `cumulativeChance` is greater than the random number, that's our pick.

### To illustrate the solution think about this scenario:

Probabilities:

GREEN => 0.1 <br/>
BLUE => 0.2 <br/>
RED => 0.3 <br/>
PINK => 0.4 <br/>

**Total => 1.0**

Cumulative Probabilities:

`[0.1, 0.3, 0.6, 1.0]`

So for any given number `n` in the range `[0, 1)` we need to check on which 'slot' it falls. For instance:

`n=0.09` would be `GREEN` since it falls in the slot `[0, 0.1)`

`n=0.25` would be `BLUE` since it falls in the slot `[0.1, 0.3)`

`n=0.40` would be `RED` since it falls in the slot `[0.3, 0.6)`

`n=0.99` would be `PINK` since it falls in the slot `[0.6, 1.0)`

## Time Complexity

Worst case: `O(n)`

Being `n` the amount of Jelly Bean flavors in the Jar.

## Space Complexity

`O(n)`

Since we have to take into account that we are storing the probabilities.

