package com.example.notifications.core.retry;

public interface RetryPolicy {

    boolean shouldRetry(int attempt, Exception exception);

    long getDelayMillis(int attempt);

}