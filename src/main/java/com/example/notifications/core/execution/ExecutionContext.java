package com.example.notifications.core.execution;

import com.example.notifications.core.circuit.CircuitBreakerRegistry;
import com.example.notifications.core.retry.RetryPolicy;

public final class ExecutionContext {

    private final RetryPolicy retryPolicy;

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public ExecutionContext(
            RetryPolicy retryPolicy,
            CircuitBreakerRegistry circuitBreakerRegistry) {

        this.retryPolicy = retryPolicy;
        this.circuitBreakerRegistry = circuitBreakerRegistry;

    }

    public RetryPolicy getRetryPolicy() {

        return retryPolicy;

    }

    public CircuitBreakerRegistry getCircuitBreakerRegistry() {

        return circuitBreakerRegistry;

    }

}