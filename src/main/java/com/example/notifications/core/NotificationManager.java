package com.example.notifications.core;

import com.example.notifications.core.execution.ExecutionContext;
import com.example.notifications.core.retry.RetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.event.NotificationEvent;
import com.example.notifications.event.NotificationEventType;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public final class NotificationManager {

    private final ChannelResolver resolver;

    private final ExecutorService executor;

    private final ExecutionContext executionContext;

    private final EventBus eventBus;

    public NotificationManager(
            ChannelResolver resolver,
            ExecutionContext executionContext,
            EventBus eventBus) {

        this.resolver = resolver;

        this.executionContext = executionContext;

        this.eventBus = eventBus;

        this.executor = Executors.newFixedThreadPool(10);

    }

    // =========================
    // SYNC
    // =========================

    public NotificationResult send(Notification notification) {

        if (!executionContext.getCircuitBreaker().allowRequest()) {

            throw new IllegalStateException("Circuit is OPEN");

        }

        try {

            NotificationResult result =
                    resolver.resolve(notification);

            if (result.getStatus().name().equals("FAILED")) {

                executionContext.getCircuitBreaker().recordFailure();

                eventBus.publish(
                        new NotificationEvent(
                                NotificationEventType.FAILED,
                                result,
                                Instant.now()
                        )
                );

            } else {

                executionContext.getCircuitBreaker().recordSuccess();

                eventBus.publish(
                        new NotificationEvent(
                                NotificationEventType.SENT,
                                result,
                                Instant.now()
                        )
                );

            }

            return result;

        } catch (Exception ex) {

            eventBus.publish(
                    new NotificationEvent(
                            NotificationEventType.FAILED,
                            null,
                            Instant.now()
                    )
            );

            throw ex;

        }

    }

    // =========================
    // ASYNC
    // =========================

    public CompletableFuture<NotificationResult> sendAsync(
            Notification notification) {

        return CompletableFuture.supplyAsync(() -> send(notification), executor);

    }

    // =========================
    // BATCH
    // =========================

    public List<NotificationResult> sendBatch(
            List<Notification> notifications) {

        return notifications.stream()
                .map(this::send)
                .collect(Collectors.toList());

    }

    public CompletableFuture<List<NotificationResult>> sendBatchAsync(
            List<Notification> notifications) {

        List<CompletableFuture<NotificationResult>> futures =
                notifications.stream()
                        .map(this::sendAsync)
                        .toList();

        return CompletableFuture.allOf(
                        futures.toArray(new CompletableFuture[0]))
                .thenApply(v ->
                        futures.stream()
                                .map(CompletableFuture::join)
                                .toList()
                );
    }

}