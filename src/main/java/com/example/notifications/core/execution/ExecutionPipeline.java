package com.example.notifications.core.execution;

import com.example.notifications.core.ChannelResolver;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.exception.CircuitBreakerOpenException;
import com.example.notifications.event.EventBus;
import com.example.notifications.event.NotificationEvent;
import com.example.notifications.event.NotificationEventType;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.validation.ValidationRegistry;

import java.time.Instant;

public final class ExecutionPipeline {

    private final ChannelResolver resolver;

    private final ExecutionContext context;

    private final EventBus eventBus;

    private final ValidationRegistry validationRegistry;

    public ExecutionPipeline(
            ChannelResolver resolver,
            ExecutionContext context,
            EventBus eventBus,
            ValidationRegistry validationRegistry) {

        this.resolver = resolver;
        this.context = context;
        this.eventBus = eventBus;
        this.validationRegistry = validationRegistry;

    }

    public NotificationResult execute(
            Notification notification) {

        validationRegistry.validate(notification);

        CircuitBreaker breaker =
                context.getCircuitBreakerRegistry()
                        .get(notification.getChannel());

        if (!breaker.allowRequest()) {

            throw new CircuitBreakerOpenException(
                    "Circuit is OPEN");

        }

        RetryExecutor retryExecutor =
                new RetryExecutor(
                        context.getRetryPolicy());

        NotificationResult result =
                retryExecutor.execute(
                        () -> resolver.resolve(notification));

        if (result.getStatus() == NotificationStatus.FAILED) {

            breaker.recordFailure();

            eventBus.publish(
                    new NotificationEvent(
                            NotificationEventType.FAILED,
                            result,
                            Instant.now()));

        } else {

            breaker.recordSuccess();

            eventBus.publish(
                    new NotificationEvent(
                            NotificationEventType.SENT,
                            result,
                            Instant.now()));

        }

        return result;

    }

}