package com.ulisesbocchio.github.puzzles.ratelimiter;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author Ulises Bocchio
 */
@SuppressWarnings("Duplicates")
public class SimpleSmoothRateLimiter implements RateLimiter {
    private Stopwatch stopwatch;
    private boolean slotAvailable = true;
    private long slotTime;
    private long currentSlot = 0;

    public SimpleSmoothRateLimiter(int rate) {
        this(rate, Stopwatch.createStarted());
    }

    @VisibleForTesting
    public SimpleSmoothRateLimiter(int rate, Stopwatch stopwatch) {
        Preconditions.checkArgument(rate < 1000, "Rate must be < 1000");
        double smoothFactor = 1d / (double)rate;
        this.slotTime = (long) (1000L * smoothFactor);
        this.stopwatch = stopwatch;
    }

    public boolean acquire() {
        maybeResetSlot();
        if(slotAvailable) {
            slotAvailable = false;
            return true;
        }
        return false;
    }

    private void maybeResetSlot() {
        long elapsedSlots = stopwatch.elapsed(TimeUnit.MILLISECONDS) / slotTime;
        if (elapsedSlots > currentSlot) {
            slotAvailable = true;
            currentSlot = elapsedSlots;
        }
    }
}
