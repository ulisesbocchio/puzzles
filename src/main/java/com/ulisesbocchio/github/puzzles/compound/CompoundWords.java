package com.ulisesbocchio.github.puzzles.compound;

import java.util.Set;

/**
 * @author Ulises Bocchio
 */
public class CompoundWords {
    public static boolean isCompound(String word, Set<String> dictionary) {
        boolean isCompound = false;
        if(dictionary.contains(word)) {
            for(int i = 1; i < word.length() -1; i++) {
                if (dictionary.contains(word.substring(0, i)) &&
                        dictionary.contains(word.substring(i, word.length()))) {
                    isCompound = true;
                }
            }
        }
        return isCompound;
    }
}
