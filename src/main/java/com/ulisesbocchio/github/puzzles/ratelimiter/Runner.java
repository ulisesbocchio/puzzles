package com.ulisesbocchio.github.puzzles.ratelimiter;

import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author Ulises Bocchio
 */
public class Runner {
    public static void main(String[] args) {
        System.out.println("Bursty");
        run(new BurstyRateLimiter(5), 10000);

        System.out.println("\n\nSmooth Simple");
        run(new SimpleSmoothRateLimiter(5), 10000);

        System.out.println("\n\nSmooth");
        run(new SmoothRateLimiter(5, 0.5d), 10000);
    }

    @SneakyThrows
    private static void run(RateLimiter limiter, long millis) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        int counter = 0;
        long seconds = 0;
        while (stopwatch.elapsed(TimeUnit.MILLISECONDS) < millis) {
            long currentSeconds = stopwatch.elapsed(TimeUnit.SECONDS);
            if (currentSeconds > seconds) {
                seconds = currentSeconds;
                System.out.println("Elapsed seconds: " + seconds);
            }

            if (limiter.acquire()) {
                ++counter;
                System.out.println(counter);
            }
            Thread.sleep(1L);
        }
    }
}
