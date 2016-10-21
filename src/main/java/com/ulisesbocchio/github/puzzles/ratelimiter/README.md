# Rate Limiter

## Problem
Write a Rate Limiter. A class basically with an `acquire():boolean` method that returns `true` when a rate slot is
available for use and false otherwise. The class instance is initialized with a `rate` or `QPS`, "queries per second"
that the rate limiter can accept every second.

## Solutions

### [RateLimiter](./RateLimiter.java)

Rough, based the `rate` value for any given queries within a second in only allows up to `rate` amount of queries and
after `rate` has been reach it denies access until the following second.

### [SimpleSmoothRateLimiter](./SimpleSmoothRateLimiter.java)

Smooth implementation, meaning that not all slots can be consumed at once and then the program has to wait until the
next second. Instead, a `smoothFactor` is used, which is always `1 / rate`, meaning that there will an available slot
every `1 / rate` of a second. The Slot Time (`slotTime`) is always `1 / rate * 1000`, so every `1 / rate * 1000` there
will be one slot available. This solution only allows rates up to `1000 QPS` due to the fact that it uses `milliseconds`.

### [SmoothRateLimiter](./SmoothRateLimiter.java)

Smooth implementation, similar to the previous implementation. The main difference is that `rate` and `smoothFactor` are
configurable and the rate is not limited to `1000`, although it uses `milliseconds` logic also.<br/>
 <br/>.`smoothFactor` is not limited to `1 / rate` and the amount `rate * smoothFactor` becomes the amount of slots per
 period, and each period is `1000 * smoothFactor`. So for every period, there's a `slotsPerPeriod` amount that can be
 given out in bursts within the period time. The smaller the period time, the smoother limiter perception.
 `smoothFactor` has to be greater than (or equal) to `1 / rate` to guarantee at least `1` slot per period.

### To illustrate the solution think about this scenario:

For the Smooth Rate Limiter, image values `rate=4` and `smoothFactor=0.5`. This means that:

`period=500ms`

`slotsPeriod=2`

This means that every 500ms, the limiter will accept 2 queries as they come, and then wait till the next period to
be able to accept 2 more. The same repeats.

## Test

 Tests are in class [RateLimiterTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/ratelimiter/RateLimiterTest.java)
 Tests are in class [SmoothRateLimiterTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/ratelimiter/SmoothRateLimiterTest.java)
 Tests are in class [SimpleSmoothRateLimiterTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/ratelimiter/SimpleSmoothRateLimiterTest.java)

## Time Complexity

`O(1)`

## Space Complexity

`O(1)`