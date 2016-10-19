package com.ulisesbocchio.github.puzzles.missingnumber;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author Ulises Bocchio
 */
public class MissingNumberTest {

    @Test
    public void testOptimal() {
        Assertions.assertThat(MissingNumber.missingNumberOptimal(new int[]{2, 3, 4, 5})).isEqualTo(1);
        Assertions.assertThat(MissingNumber.missingNumberOptimal(new int[]{3, 2, 4, 1})).isEqualTo(5);
        Assertions.assertThat(MissingNumber.missingNumberOptimal(new int[]{3, 2, 5, 1})).isEqualTo(4);
    }

    @Test
    public void testLinear() {
        Assertions.assertThat(MissingNumber.missingNumberLinear(new int[]{2, 3, 4, 5})).isEqualTo(1);
        Assertions.assertThat(MissingNumber.missingNumberLinear(new int[]{3, 2, 4, 1})).isEqualTo(5);
        Assertions.assertThat(MissingNumber.missingNumberLinear(new int[]{3, 2, 5, 1})).isEqualTo(4);
    }

    @Test
    public void testWorse() {
        Assertions.assertThat(MissingNumber.missingNumberWorse(new int[]{2, 3, 4, 5})).isEqualTo(1);
        Assertions.assertThat(MissingNumber.missingNumberWorse(new int[]{3, 2, 4, 1})).isEqualTo(5);
        Assertions.assertThat(MissingNumber.missingNumberWorse(new int[]{3, 2, 5, 1})).isEqualTo(4);
    }

}