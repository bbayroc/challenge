package com.example.notifications.model.email;

import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationPriority;

import java.util.Map;

public final class EmailNotification extends Notification {

    private final String recipient;
    private final String subject;
    private final String message;

    private EmailNotification(Builder builder) {

        super(
                NotificationChannel.EMAIL,
                builder.priority,
                builder.metadata);

        this.recipient = builder.recipient;
        this.subject = builder.subject;
        this.message = builder.message;

    }

    public static Builder builder() {

        return new Builder();

    }

    public String getRecipient() {

        return recipient;

    }

    public String getSubject() {

        return subject;

    }

    public String getMessage() {

        return message;

    }

    public static final class Builder {

        private String recipient;

        private String subject;

        private String message;

        private NotificationPriority priority =
                NotificationPriority.NORMAL;

        private Map<String, String> metadata =
                Map.of();

        public Builder recipient(String recipient) {

            this.recipient = recipient;

            return this;

        }

        public Builder subject(String subject) {

            this.subject = subject;

            return this;

        }

        public Builder message(String message) {

            this.message = message;

            return this;

        }

        public Builder priority(
                NotificationPriority priority) {

            this.priority = priority;

            return this;

        }

        public Builder metadata(
                Map<String, String> metadata) {

            this.metadata = metadata;

            return this;

        }

        public EmailNotification build() {

            return new EmailNotification(this);

        }

    }

}