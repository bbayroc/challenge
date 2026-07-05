package com.example.notifications.core.retry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetryPolicyTest {

    @Test
    void shouldStopRetryingAfterMaxAttempts() {

        ExponentialBackoffRetryPolicy policy =
                new ExponentialBackoffRetryPolicy(3);

        RuntimeException ex =
                new RuntimeException("failure");

        assertTrue(policy.shouldRetry(0, ex));
        assertTrue(policy.shouldRetry(1, ex));
        assertTrue(policy.shouldRetry(2, ex));

        assertFalse(policy.shouldRetry(3, ex));

    }

    @Test
    void shouldIncreaseDelayExponentially() {

        ExponentialBackoffRetryPolicy policy =
                new ExponentialBackoffRetryPolicy(5);

        assertEquals(100L, policy.getDelayMillis(0));
        assertEquals(200L, policy.getDelayMillis(1));
        assertEquals(400L, policy.getDelayMillis(2));

    }

}