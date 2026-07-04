package com.example.notifications.event;

import com.example.notifications.model.NotificationResult;

import java.time.Instant;

public final class NotificationEvent {

    private final NotificationEventType type;

    private final NotificationResult result;

    private final Instant timestamp;

    public NotificationEvent(
            NotificationEventType type,
            NotificationResult result,
            Instant timestamp) {

        this.type = type;

        this.result = result;

        this.timestamp = timestamp;

    }

    public NotificationEventType getType() {
        return type;
    }

    public NotificationResult getResult() {
        return result;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}