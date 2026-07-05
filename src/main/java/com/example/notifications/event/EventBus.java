package com.example.notifications.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class EventBus {

    private final List<NotificationEventListener> listeners =
            new CopyOnWriteArrayList<>();

    private final ExecutorService executor;

    private final boolean async;

    public EventBus() {
        this(false);
    }

    public EventBus(boolean async) {

        this.async = async;

        this.executor =
                Executors.newVirtualThreadPerTaskExecutor();

    }

    public void register(NotificationEventListener listener) {
        listeners.add(listener);
    }

    public void publish(NotificationEvent event) {

        if (!async) {
            publishSync(event);
            return;
        }

        publishAsync(event);
    }

    private void publishSync(NotificationEvent event) {
        for (NotificationEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }

    private void publishAsync(NotificationEvent event) {
        for (NotificationEventListener listener : listeners) {
            CompletableFuture.runAsync(
                    () -> listener.onEvent(event),
                    executor
            );
        }
    }

    public void shutdown() {
        executor.shutdown();
    }

}