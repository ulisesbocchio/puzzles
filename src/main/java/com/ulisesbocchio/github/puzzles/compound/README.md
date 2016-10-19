# Compound Words

## Problem
Find out whether a given word is compound or not given a dictionary of words.
A compound word by definition is a new word form by the juxtaposition of 2 other words. For instance:

* breakfast
* ballroom
* softball

Are compound words. Then, other words such as:
 
 * cat
 * dog
 * catdog
 
 Are not compound words. Notice that "catdog" is formed by 2 other words but it doesn't mean anything in itself.
 
 A dictionary is provided with existing words, for instance:
 \[break, fast, breakfast, cat, dog\] is enough of a dictionary to figure out that "breakfast" is a compound word.

## Solution

Divide and conquer. The solution consists on iterating over the length of the provided word and and split the word in
half incrementally from `1` to `word.length - 1` and check whether both halves exist in the dictionary. Also, an initial 
check must be made to make sure the whole word is actually a valid word too.

### To illustrate the solution think about this scenario:

#### Dictionary

\[break, fast, breakfast, cat, dog\]

#### Word

breakfast

#### Simulation

it 1: b reakfast <br/>
it 2: br eakfast <br/>
it 3: bre akfast <br/>
it 4: brea kfast <br/>
it 5: break fast <br/>

After five iterations we can find that since "breakfast" was a word and "break" and "fast" are also words, then
"breakfast" is a compound word.

## Test

Test is in class [CompoundWordsTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/compound/CompoundWordsTest.java)

## Time Complexity

Worst case: `O(n^2)`

Being `n` the amount of letters in the word. Each iteration needs to split the word in half iterating over the entire
word.

## Space Complexity

`O(n)`

Since each iteration needs to store a copy of the word in 2 halves.