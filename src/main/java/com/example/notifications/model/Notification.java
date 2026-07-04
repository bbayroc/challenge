package com.example.notifications.model;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Notification {

    private final UUID id;

    private final Instant createdAt;

    private final NotificationChannel channel;

    private final NotificationPriority priority;

    private final Map<String, String> metadata;

    protected Notification(
            NotificationChannel channel,
            NotificationPriority priority,
            Map<String, String> metadata) {

        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.channel = channel;
        this.priority = priority == null
                ? NotificationPriority.NORMAL
                : priority;

        this.metadata = metadata == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(
                new HashMap<>(metadata));

    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public NotificationPriority getPriority() {
        return priority;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

}