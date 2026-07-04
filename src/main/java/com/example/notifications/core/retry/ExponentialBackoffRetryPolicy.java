package com.example.notifications.core.retry;

public final class ExponentialBackoffRetryPolicy
        implements RetryPolicy {

    private final int maxAttempts;

    public ExponentialBackoffRetryPolicy(int maxAttempts) {

        this.maxAttempts = maxAttempts;

    }

    @Override
    public boolean shouldRetry(int attempt, Exception exception) {

        return attempt < maxAttempts;

    }

    @Override
    public long getDelayMillis(int attempt) {

        return (long) Math.pow(2, attempt) * 100L;

    }

}