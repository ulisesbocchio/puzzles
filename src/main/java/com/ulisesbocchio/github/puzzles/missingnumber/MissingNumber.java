package com.ulisesbocchio.github.puzzles.missingnumber;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Ulises Bocchio
 */
public class MissingNumber {
    public static int missingNumberOptimal(int[] numbers) {
        int n = numbers.length + 1;
        int actualSum = Arrays.stream(numbers).sum();
        int sum = n * (n + 1) / 2;
        return sum - actualSum;
    }

    public static int missingNumberLinear(int [] numbers) {
        int n = numbers.length + 1;
        int actualSum = Arrays.stream(numbers).sum();
        int sum = IntStream.rangeClosed(1, n).sum();
        return sum - actualSum;
    }

    public static int missingNumberWorse(int [] numbers) {
        Arrays.sort(numbers);
        int i = 0;
        while(i < numbers.length && numbers[i] == i + 1) {
            i++;
        }
        return i + 1;
    }
}
