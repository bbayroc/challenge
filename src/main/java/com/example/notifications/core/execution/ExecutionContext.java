package com.example.notifications.core.execution;

import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.RetryPolicy;

public final class ExecutionContext {

    private final RetryPolicy retryPolicy;

    private final CircuitBreaker circuitBreaker;

    public ExecutionContext(
            RetryPolicy retryPolicy,
            CircuitBreaker circuitBreaker) {

        this.retryPolicy = retryPolicy;

        this.circuitBreaker = circuitBreaker;

    }

    public RetryPolicy getRetryPolicy() {

        return retryPolicy;

    }

    public CircuitBreaker getCircuitBreaker() {

        return circuitBreaker;

    }

}