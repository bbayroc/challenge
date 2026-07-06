package com.example.notifications.core.retry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetryPolicyTest {

    @Test
    void shouldStopRetryingAfterMaxAttempts() {

        RetryPolicy policy =
                new ExponentialBackoffRetryPolicy(3);

        RuntimeException exception =
                new RuntimeException("failure");

        assertAll(

                () -> assertTrue(
                        policy.shouldRetry(0, exception)),

                () -> assertTrue(
                        policy.shouldRetry(1, exception)),

                () -> assertTrue(
                        policy.shouldRetry(2, exception)),

                () -> assertFalse(
                        policy.shouldRetry(3, exception))

        );

    }

    @Test
    void shouldIncreaseDelayExponentially() {

        RetryPolicy policy =
                new ExponentialBackoffRetryPolicy(5);

        assertAll(

                () -> assertEquals(
                        100L,
                        policy.getDelayMillis(0)),

                () -> assertEquals(
                        200L,
                        policy.getDelayMillis(1)),

                () -> assertEquals(
                        400L,
                        policy.getDelayMillis(2))

        );

    }

}