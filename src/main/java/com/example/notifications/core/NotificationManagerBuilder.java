package com.example.notifications.core;

import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.circuit.CircuitBreakerRegistry;
import com.example.notifications.core.execution.ExecutionContext;
import com.example.notifications.core.retry.RetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.exception.ConfigurationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class NotificationManagerBuilder {

    private final List<ChannelHandler<?>> handlers =
            new ArrayList<>();

    private RetryPolicy retryPolicy;

    private CircuitBreaker circuitBreaker;

    private EventBus eventBus =
            new EventBus();

    public NotificationManagerBuilder handler(
            ChannelHandler<?> handler) {

        handlers.add(handler);

        return this;

    }

    public NotificationManagerBuilder handlers(
            Collection<? extends ChannelHandler<?>> handlers) {

        this.handlers.addAll(handlers);

        return this;

    }

    public NotificationManagerBuilder retryPolicy(
            RetryPolicy retryPolicy) {

        this.retryPolicy = retryPolicy;

        return this;

    }

    public NotificationManagerBuilder circuitBreaker(
            CircuitBreaker circuitBreaker) {

        this.circuitBreaker = circuitBreaker;

        return this;

    }

    public NotificationManagerBuilder eventBus(
            EventBus eventBus) {

        this.eventBus = eventBus;

        return this;

    }

    public NotificationManager build() {

        if (handlers.isEmpty()) {

            throw new ConfigurationException(
                    "At least one ChannelHandler is required");

        }

        if (retryPolicy == null) {

            throw new ConfigurationException(
                    "RetryPolicy is required");

        }

        if (circuitBreaker == null) {

            throw new ConfigurationException(
                    "CircuitBreaker is required");

        }

        ChannelResolver resolver =
                new ChannelResolver(handlers);

        CircuitBreakerRegistry registry =
                new CircuitBreakerRegistry()

                        .register(
                                NotificationChannel.EMAIL,
                                circuitBreaker.copy())

                        .register(
                                NotificationChannel.SMS,
                                circuitBreaker.copy())

                        .register(
                                NotificationChannel.PUSH,
                                circuitBreaker.copy());

        ExecutionContext executionContext =
                new ExecutionContext(
                        retryPolicy,
                        registry);

        return new NotificationManager(
                resolver,
                executionContext,
                eventBus);

    }

}