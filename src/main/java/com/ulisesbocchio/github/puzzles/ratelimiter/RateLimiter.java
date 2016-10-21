package com.ulisesbocchio.github.puzzles.ratelimiter;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author Ulises Bocchio
 */
public class RateLimiter {
    private int rate;
    private Stopwatch stopWatch;
    private long count = 0;
    private long currentSecond = 0;

    public RateLimiter(int rate) {
        this(rate, Stopwatch.createStarted());
    }

    @VisibleForTesting
    public RateLimiter(int rate, Stopwatch stopwatch) {
        this.rate = rate;
        this.stopWatch = stopwatch;
    }

    public boolean acquire() {
        maybeResetCount();

        if (count < rate) {
            count++;
            return true;
        }
        return false;
    }

    private void maybeResetCount() {
        long elapsedSeconds = stopWatch.elapsed(TimeUnit.SECONDS);
        if (elapsedSeconds > currentSecond) {
            count = 0;
            currentSecond = elapsedSeconds;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter(5);
        int counter = 0;
        while (true) {
            if(limiter.acquire()) {
                System.out.println(++counter);
            }
            Thread.sleep(10L);
        }
    }
}
