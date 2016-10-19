package com.ulisesbocchio.github.puzzles.jellybean;

import com.google.common.base.Preconditions;
import lombok.Getter;

/**
 * @author ulises
 */
public class JellyBeanDef {
    @Getter
    private double chance;
    @Getter
    private JellyBean jellyBean;

    public JellyBeanDef(double chance, JellyBean jellyBean) {
        Preconditions.checkArgument(chance > 0, "chance must be > 0");
        Preconditions.checkArgument(jellyBean != null, "jelly bean can't be null");
        this.chance = chance;
        this.jellyBean = jellyBean;
    }
}
