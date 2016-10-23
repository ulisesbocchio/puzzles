# Rate Limiter

## Problem
A class basically with an `acquire():boolean` method that returns `true` when a slot is
available for use and false otherwise. The class instance is initialized with a `rate` or `QPS`, "queries per second"
that the rate limiter can accept every second.

## Solutions

### [BurstyRateLimiter](./BurstyRateLimiter.java)

Bursty, based on a `rate` value, for any given queries within a second it allows up to `rate` amount of slots and
after `rate` has been reach it denies queries until the following second. So if a thread is trying to acquire slots
constantly it will burst through all the slots very quickly and a "quiet" period will follow until the next second
starts, where a new burst will commence, and so on.

### [SimpleSmoothRateLimiter](./SimpleSmoothRateLimiter.java)

Smooth, meaning that not all slots can be consumed at once in bursts. Instead, an internal `smoothFactor` is used, which is
always `1 / rate`, meaning that there will an available slot every `1 / rate` of a second. The Slot Time (`slotTime`) is
then `1 / rate * 1000`, so every `1 / rate * 1000` millis there will be one slot available. This solution only allows
rates up to `1000 QPS` due to the fact that it uses `millisecond` logic.

### [SmoothRateLimiter](./SmoothRateLimiter.java)

Smooth, similar to the previous implementation. The main difference is that `rate` and `smoothFactor` are
configurable and the rate is not limited to `1000`, although it uses `milliseconds` logic also.<br/>
This means `smoothFactor` is not limited to `1 / rate` and the amount `rate * smoothFactor` becomes the amount of slots per
period, and each period is `1000 * smoothFactor`. So for every period, there's a `slotsPerPeriod` amount that can be
acquired in bursts within the period time. The smaller the period time (up to 1ms), the less slots per period.
`smoothFactor` has to be in the range \[0.001, 1\] to guarantee slots between 1-1000ms.

### To illustrate the solution think about this scenario:

For the Smooth Rate Limiter, image values `rate=4` and `smoothFactor=0.5`. This means that:

`period=500ms`

`slotsPeriod=2`

This means that every 500ms, the limiter will accept 2 queries as they come, and then wait till the next period to
be able to accept 2 more. The same repeats.

```java
  /**
   *
   *          ^ slots enabled
   *          |
   *          |           
   *          |           
   *          |           
   *        2 +--+  +--+  +--+  +--+  +--+  +--+
   *    slots |  |  |  |  |  |  |  |  |  |  |  |
   *          |  |  |  |  |  |  |  |  |  |  |  |  
   *          |  |  |  |  |  |  |  |  |  |  |  |  
   *        0 +-----+-----+-----+-----+-----+-----+--> time
   *               0.5s   1s   1.5s   2s   2.5s   3s
   */   
```

## Test

[Runner](./Runner.java) Runs all 3 rate limiters for 10 seconds with different rates.

 Unit Tests are in classes:
 * [BurstyRateLimiterTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/ratelimiter/BurstyRateLimiterTest.java)
 * [SmoothRateLimiterTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/ratelimiter/SmoothRateLimiterTest.java)
 * [SimpleSmoothRateLimiterTest](../../../../../../../test/java/com/ulisesbocchio/github/puzzles/ratelimiter/SimpleSmoothRateLimiterTest.java)

## Time Complexity

`O(1)`

## Space Complexity

`O(1)`