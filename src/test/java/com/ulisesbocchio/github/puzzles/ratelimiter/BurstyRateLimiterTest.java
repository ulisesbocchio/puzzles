package com.ulisesbocchio.github.puzzles.ratelimiter;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Ulises Bocchio
 */
public class BurstyRateLimiterTest {

    private static final long NS_IN_MS = 1000000L;

    @Test
    public void testAcquire() throws Exception {
        Ticker ticker = Mockito.mock(Ticker.class);
        //First read is to establish start time
        Mockito.when(ticker.read()).thenReturn(ns(0), ns(1), ns(250), ns(500), ns(999), ns(1500), ns(3000), ns(5000));
        Stopwatch stopwatch = Stopwatch.createStarted(ticker);
        BurstyRateLimiter rateLimiter = new BurstyRateLimiter(2, stopwatch);

        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
    }

    private long ns(long ms) {
        return NS_IN_MS * ms;
    }

}