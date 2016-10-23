package com.ulisesbocchio.github.puzzles.ratelimiter;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Ulises Bocchio
 */
public class SmoothRateLimiterTest {

    private static final long NS_IN_MS = 1000000L;

    @Test
    public void testAcquireSimple() throws Exception {
        Ticker ticker = Mockito.mock(Ticker.class);
        //First read is to establish start time
        Mockito.when(ticker.read()).thenReturn(ns(0), ns(0), ns(100), ns(200), ns(300), ns(400), ns(500), ns(600),
                ns(700), ns(800), ns(900), ns(1000));
        Stopwatch stopwatch = Stopwatch.createStarted(ticker);
        SmoothRateLimiter rateLimiter = new SmoothRateLimiter(4, stopwatch);

        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
    }

    @Test
    public void testAcquireCustomSmooth() throws Exception {
        Ticker ticker = Mockito.mock(Ticker.class);
        //First read is to establish start time
        Mockito.when(ticker.read()).thenReturn(ns(0), ns(0), ns(100), ns(200), ns(300), ns(400), ns(500), ns(600),
                ns(700), ns(800), ns(900), ns(1000));
        Stopwatch stopwatch = Stopwatch.createStarted(ticker);
        SmoothRateLimiter rateLimiter = new SmoothRateLimiter(4, 0.5, stopwatch);

        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
    }

    @Test
    public void testAcquireCustomBursty() throws Exception {
        Ticker ticker = Mockito.mock(Ticker.class);
        //First read is to establish start time
        Mockito.when(ticker.read()).thenReturn(ns(0), ns(0), ns(100), ns(200), ns(300), ns(400), ns(500), ns(600),
                ns(700), ns(800), ns(900), ns(1000));
        Stopwatch stopwatch = Stopwatch.createStarted(ticker);
        SmoothRateLimiter rateLimiter = new SmoothRateLimiter(4, 1, stopwatch);

        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
    }

    @Test
    public void testAcquireSamePeriodSimple() throws Exception {
        Ticker ticker = Mockito.mock(Ticker.class);
        //First read is to establish start time
        Mockito.when(ticker.read()).thenReturn(ns(0), ns(250), ns(250), ns(250), ns(250), ns(250), ns(250), ns(250));
        Stopwatch stopwatch = Stopwatch.createStarted(ticker);
        SmoothRateLimiter rateLimiter = new SmoothRateLimiter(4, stopwatch);

        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
    }

    @Test
    public void testAcquireSamePeriodCustomSmooth() throws Exception {
        Ticker ticker = Mockito.mock(Ticker.class);
        //First read is to establish start time
        Mockito.when(ticker.read()).thenReturn(ns(0), ns(250), ns(250), ns(250), ns(250), ns(250), ns(250), ns(250));
        Stopwatch stopwatch = Stopwatch.createStarted(ticker);
        SmoothRateLimiter rateLimiter = new SmoothRateLimiter(4, 0.5, stopwatch);

        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
    }

    @Test
    public void testAcquireSamePeriodCustomBursty() throws Exception {
        Ticker ticker = Mockito.mock(Ticker.class);
        //First read is to establish start time
        Mockito.when(ticker.read()).thenReturn(ns(0), ns(250), ns(250), ns(250), ns(250), ns(250), ns(250), ns(250));
        Stopwatch stopwatch = Stopwatch.createStarted(ticker);
        SmoothRateLimiter rateLimiter = new SmoothRateLimiter(4, 1, stopwatch);

        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isTrue();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
        Assertions.assertThat(rateLimiter.acquire()).isFalse();
    }

    private long ns(long ms) {
        return NS_IN_MS * ms;
    }

}