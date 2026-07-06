package com.example.notifications.core;

import com.example.notifications.core.execution.ExecutionContext;
import com.example.notifications.core.execution.ExecutionPipeline;
import com.example.notifications.event.EventBus;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.validation.EmailValidator;
import com.example.notifications.validation.PushValidator;
import com.example.notifications.validation.SmsValidator;
import com.example.notifications.validation.ValidationRegistry;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public final class NotificationManager
        implements AutoCloseable {

    private final ExecutionPipeline pipeline;
    private final ExecutorService executor;
    private final EventBus eventBus;

    public NotificationManager(
            ChannelResolver resolver,
            ExecutionContext executionContext,
            EventBus eventBus) {

        this.eventBus = eventBus;

        ValidationRegistry validationRegistry =
                new ValidationRegistry()
                        .register(new EmailValidator())
                        .register(new SmsValidator())
                        .register(new PushValidator());

        this.pipeline =
                new ExecutionPipeline(
                        resolver,
                        executionContext,
                        eventBus,
                        validationRegistry);

        this.executor =
                Executors.newVirtualThreadPerTaskExecutor();

    }

    public static NotificationManagerBuilder builder() {

        return new NotificationManagerBuilder();

    }

    public NotificationResult send(
            Notification notification) {

        return pipeline.execute(notification);

    }

    public CompletableFuture<NotificationResult> sendAsync(
            Notification notification) {

        return CompletableFuture.supplyAsync(
                () -> send(notification),
                executor);

    }

    public List<NotificationResult> sendBatch(
            List<Notification> notifications) {

        return notifications.stream()
                .map(this::send)
                .collect(Collectors.toList());

    }

    public CompletableFuture<List<NotificationResult>>
    sendBatchAsync(
            List<Notification> notifications) {

        List<CompletableFuture<NotificationResult>> futures =
                notifications.stream()
                        .map(this::sendAsync)
                        .toList();

        return CompletableFuture
                .allOf(
                        futures.toArray(
                                new CompletableFuture[0]))
                .thenApply(v ->
                        futures.stream()
                                .map(CompletableFuture::join)
                                .toList());

    }

    @Override
    public void close() {

        eventBus.shutdown();
        executor.shutdown();

        try {

            if (!executor.awaitTermination(
                    5,
                    TimeUnit.SECONDS)) {

                executor.shutdownNow();

            }

        } catch (InterruptedException ex) {

            executor.shutdownNow();

            Thread.currentThread().interrupt();

        }

    }

}