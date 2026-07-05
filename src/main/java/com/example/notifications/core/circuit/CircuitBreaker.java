package com.example.notifications.core.circuit;

public final class CircuitBreaker {

    private final int failureThreshold;

    private final long resetTimeoutMillis;

    private volatile int failureCount;

    private volatile CircuitState state =
            CircuitState.CLOSED;

    private volatile long lastFailureTime;

    public CircuitBreaker(
            int failureThreshold,
            long resetTimeoutMillis) {

        this.failureThreshold = failureThreshold;

        this.resetTimeoutMillis = resetTimeoutMillis;

    }

    public synchronized boolean allowRequest() {

        if (state == CircuitState.OPEN) {

            if (System.currentTimeMillis() - lastFailureTime
                    > resetTimeoutMillis) {

                state = CircuitState.HALF_OPEN;

                return true;

            }

            return false;

        }

        return true;

    }

    public synchronized void recordSuccess() {

        failureCount = 0;

        state = CircuitState.CLOSED;

    }

    public synchronized void recordFailure() {

        failureCount++;

        lastFailureTime = System.currentTimeMillis();

        if (failureCount >= failureThreshold) {

            state = CircuitState.OPEN;

        }

    }

}