package com.ulisesbocchio.github.puzzles.ratelimiter;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author Ulises Bocchio
 */
@SuppressWarnings("Duplicates")
public class SmoothRateLimiter {
    private Stopwatch stopwatch;
    private long count = 0;
    private long slotsPerPeriod;
    private long periodTime;
    private long currentPeriod = 0;

    public SmoothRateLimiter(int rate) {
        this(rate, Stopwatch.createStarted());
    }

    @VisibleForTesting
    public SmoothRateLimiter(int rate, Stopwatch stopwatch) {
        this(rate, Math.max((1d / (double) rate), (1d / 1000d)), stopwatch);
    }


    public SmoothRateLimiter(int rate, double smoothFactor) {
        this(rate, smoothFactor, Stopwatch.createStarted());
    }

    @VisibleForTesting
    public SmoothRateLimiter(int rate, double smoothFactor, Stopwatch stopwatch) {
        this.slotsPerPeriod = (long) Math.ceil(rate * smoothFactor);
        this.periodTime = (long) (1000L * smoothFactor);
        this.stopwatch = stopwatch;
    }

    public boolean acquire() {
        maybeResetCount();
        if (count < slotsPerPeriod) {
            count++;
            return true;
        }
        return false;
    }

    private void maybeResetCount() {
        long elapsedPeriods = stopwatch.elapsed(TimeUnit.MILLISECONDS) / periodTime;
        if (elapsedPeriods > currentPeriod) {
            count = 0;
            currentPeriod = elapsedPeriods;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SmoothRateLimiter limiter = new SmoothRateLimiter(5);
        int counter = 0;
        boolean printElapsed = true;
        while (true) {
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed % 1000 == 0 && printElapsed) {
                System.out.println("Elapsed millis: " + elapsed);
                printElapsed = false;
            } else if (elapsed % 1000 != 0) {
                printElapsed = true;
            }
            if (limiter.acquire()) {
                ++counter;
                System.out.println(counter);
            }
        }
    }
}
