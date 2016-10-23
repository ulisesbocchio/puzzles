package com.ulisesbocchio.github.puzzles.ratelimiter;

/**
 * @author Ulises Bocchio
 */
public interface RateLimiter {
    boolean acquire();
}
