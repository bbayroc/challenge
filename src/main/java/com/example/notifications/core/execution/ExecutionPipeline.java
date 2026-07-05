package com.example.notifications.core.execution;

import com.example.notifications.core.ChannelResolver;
import com.example.notifications.event.EventBus;
import com.example.notifications.event.NotificationEvent;
import com.example.notifications.event.NotificationEventType;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;

import java.time.Instant;

public final class ExecutionPipeline {

    private final ChannelResolver resolver;

    private final ExecutionContext context;

    private final EventBus eventBus;

    private final ValidationExecutor validationExecutor =
            new ValidationExecutor();

    public ExecutionPipeline(
            ChannelResolver resolver,
            ExecutionContext context,
            EventBus eventBus) {

        this.resolver = resolver;
        this.context = context;
        this.eventBus = eventBus;

    }

    public NotificationResult execute(
            Notification notification) {

        validationExecutor.validate(notification);

        if (!context.getCircuitBreaker().allowRequest()) {
            throw new IllegalStateException(
                    "Circuit is OPEN");
        }

        RetryExecutor retryExecutor =
                new RetryExecutor(
                        context.getRetryPolicy());

        NotificationResult result =
                retryExecutor.execute(
                        () -> resolver.resolve(notification));

        if (result.getStatus() == NotificationStatus.FAILED) {

            context.getCircuitBreaker()
                    .recordFailure();

            eventBus.publish(
                    new NotificationEvent(
                            NotificationEventType.FAILED,
                            result,
                            Instant.now()));

        } else {

            context.getCircuitBreaker()
                    .recordSuccess();

            eventBus.publish(
                    new NotificationEvent(
                            NotificationEventType.SENT,
                            result,
                            Instant.now()));

        }

        return result;

    }

}