package com.ulisesbocchio.github.puzzles.matrixpath;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ulises Bocchio
 */
public class MatrixPathGraphTest {
    @Test
    public void pathExists() throws Exception {
        int[][] graphMValid = {
                {0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 1, 0, 1}
        };
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

        int[][] graphMInvalid = {
                {0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1}
        };
        // {0=[1],
        //  1=[0, 5],
        //  2=[6],
        //  3=[],
        //  4=[],
        //  5=[1],
        //  6=[7, 2],
        //  7=[6],
        //  8=[],
        //  9=[],
        //  10=[11],
        //  11=[10]}

        Assertions.assertThat(MatrixPathGraph.pathExists(graphMValid)).isTrue();
        Assertions.assertThat(MatrixPathGraph.pathExists(graphMInvalid)).isFalse();
    }

}