package com.example.notifications.core.circuit;

public final class CircuitBreaker {

    private final int failureThreshold;

    private final long resetTimeoutMillis;

    private int failureCount;

    private CircuitState state = CircuitState.CLOSED;

    private long lastFailureTime;

    public CircuitBreaker(
            int failureThreshold,
            long resetTimeoutMillis) {

        this.failureThreshold = failureThreshold;

        this.resetTimeoutMillis = resetTimeoutMillis;

    }

    public boolean allowRequest() {

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

    public void recordSuccess() {

        failureCount = 0;

        state = CircuitState.CLOSED;

    }

    public void recordFailure() {

        failureCount++;

        lastFailureTime = System.currentTimeMillis();

        if (failureCount >= failureThreshold) {

            state = CircuitState.OPEN;

        }

    }

}