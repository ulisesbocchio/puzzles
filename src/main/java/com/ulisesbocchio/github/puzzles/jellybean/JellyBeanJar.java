package com.ulisesbocchio.github.puzzles.jellybean;

import com.google.common.base.Preconditions;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author ulises
 */
public class JellyBeanJar {

    private List<JellyBeanDef> beanDefs;

    private Random rand = new Random();

    public JellyBeanJar(List<JellyBeanDef> beanDefs, Random rand) {
        this.rand = rand;
        this.beanDefs = beanDefs;
        double sum = beanDefs.stream()
                .mapToDouble(JellyBeanDef::getChance)
                .sum();
        Preconditions.checkArgument(sum == 1, String.format("JellyBeanDef chances must sum 1.0, was: %f", sum));
        this.beanDefs = beanDefs.stream()
                .sorted(Comparator.comparing(JellyBeanDef::getChance))
                .collect(Collectors.toList());
    }

    public JellyBeanJar(List<JellyBeanDef> beanDefs) {
        this(beanDefs, new Random());
    }

    public JellyBean getBean() {
        double pick = rand.nextDouble();
        Iterator<JellyBeanDef> it = beanDefs.iterator();
        JellyBeanDef bean = it.next();
        double cumulativeChance = bean.getChance();
        while (pick > cumulativeChance) {
            bean = it.next();
            cumulativeChance += bean.getChance();
        }
        return bean.getJellyBean();
    }

}
