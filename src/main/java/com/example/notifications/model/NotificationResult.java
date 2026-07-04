package com.example.notifications.model;

import java.time.Duration;
import java.time.Instant;

public final class NotificationResult {

    private final NotificationStatus status;
    private final String provider;
    private final String messageId;
    private final String errorMessage;
    private final int statusCode;
    private final Duration duration;
    private final Instant timestamp;

    public NotificationResult(
            NotificationStatus status,
            String provider,
            String messageId,
            String errorMessage,
            int statusCode,
            Duration duration,
            Instant timestamp) {

        this.status = status;
        this.provider = provider;
        this.messageId = messageId;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.duration = duration;
        this.timestamp = timestamp;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public String getProvider() {
        return provider;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Duration getDuration() {
        return duration;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}