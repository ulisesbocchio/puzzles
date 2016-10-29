package com.ulisesbocchio.github.puzzles.ratelimiter;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author Ulises Bocchio
 */
@SuppressWarnings("Duplicates")
public class SmoothRateLimiter implements RateLimiter {
    private Stopwatch stopwatch;
    private long count = 0;
    private long slotsPerPeriod;
    private long periodTime;
    private long currentPeriod = 0;

    public SmoothRateLimiter(int rate) {
        this(rate, Stopwatch.createStarted());
    }

    @VisibleForTesting
    SmoothRateLimiter(int rate, Stopwatch stopwatch) {
        //By default we assume a smooth factor of 1/rate. This allows us
        //to be in the scenario of the simple smooth rate limiter where for each
        // period there is only 1 slot.
        this(rate, (1d / (double) rate), stopwatch);
    }


    public SmoothRateLimiter(int rate, double smoothFactor) {
        this(rate, smoothFactor, Stopwatch.createStarted());
    }

    @VisibleForTesting
    SmoothRateLimiter(int rate, double smoothFactor, Stopwatch stopwatch) {
        // Since we use millisecond logic, we accept rates greater than 1000 QPS
        // but in those cases, we limit the smoothFactor to 1/1000 which will get us
        // slots of 1 ms. On each millisecond then we can have bursts of up to rate * 0.001.
        // In a way, putting an upper limit to the smoothness of the rate limiter.
        double actualSmoothFactor = Math.max(smoothFactor, 0.001d);
        this.slotsPerPeriod = (long) Math.ceil(rate * actualSmoothFactor);
        this.periodTime = (long) (1000L * actualSmoothFactor);
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
}
