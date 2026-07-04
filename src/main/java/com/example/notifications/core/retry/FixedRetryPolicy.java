package com.example.notifications.core.retry;

public final class FixedRetryPolicy implements RetryPolicy {

    private final int maxAttempts;
    private final long delayMillis;

    public FixedRetryPolicy(
            int maxAttempts,
            long delayMillis) {

        this.maxAttempts = maxAttempts;
        this.delayMillis = delayMillis;

    }

    @Override
    public boolean shouldRetry(
            int attempt,
            Exception exception) {

        return attempt < maxAttempts;

    }

    @Override
    public long getDelayMillis(
            int attempt) {

        return delayMillis;

    }

}