package com.ulisesbocchio.github.puzzles.compound;

import com.google.common.collect.ImmutableSet;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Ulises Bocchio
 */
public class CompoundWordsTest {
    @Test
    public void isCompound() throws Exception {
        Set<String> dictionary = ImmutableSet.of("break", "breakfast", "fast", "cat", "dog");
        Assertions.assertThat(CompoundWords.isCompound("breakfast", dictionary)).isTrue();
        Assertions.assertThat(CompoundWords.isCompound("catdog", dictionary)).isFalse();
        Assertions.assertThat(CompoundWords.isCompound("cat", dictionary)).isFalse();
        Assertions.assertThat(CompoundWords.isCompound("dog", dictionary)).isFalse();
        Assertions.assertThat(CompoundWords.isCompound("break", dictionary)).isFalse();
    }

}