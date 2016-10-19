package com.ulisesbocchio.github.puzzles.jellybean;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.mockito.Mockito.mock;

/**
 * @author ulises
 */
public class JellyBeanJarTest {
    @Test
    public void getBean() throws Exception {
        Random random = mock(Random.class);
        JellyBeanJar jar = new JellyBeanJar(ImmutableList.of(new JellyBeanDef(.1, JellyBean.GREEN),
                new JellyBeanDef(.2, JellyBean.BLUE), new JellyBeanDef(.3, JellyBean.RED),
                new JellyBeanDef(.4, JellyBean.PINK)), random);

        Mockito.when(random.nextDouble()).thenReturn(0.09, 0.1, 0.29, 0.3, 0.59, 0.6, 0.99, 0.999999);

        Assertions.assertThat(jar.getBean()).isEqualTo(JellyBean.GREEN);
        Assertions.assertThat(jar.getBean()).isEqualTo(JellyBean.GREEN);
        Assertions.assertThat(jar.getBean()).isEqualTo(JellyBean.BLUE);
        Assertions.assertThat(jar.getBean()).isEqualTo(JellyBean.BLUE);
        Assertions.assertThat(jar.getBean()).isEqualTo(JellyBean.RED);
        Assertions.assertThat(jar.getBean()).isEqualTo(JellyBean.RED);
        Assertions.assertThat(jar.getBean()).isEqualTo(JellyBean.PINK);
        Assertions.assertThat(jar.getBean()).isEqualTo(JellyBean.PINK);
    }

}