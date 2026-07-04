package com.example.notifications.model.email;

import com.example.notifications.model.*;
import com.example.notifications.template.NotificationTemplate;

public final class EmailNotification implements Notification {
    private final String recipient;
    private final String subject;
    private final String message;
    private final NotificationTemplate template;

    private EmailNotification(Builder b) {
        this.recipient = b.recipient;
        this.subject = b.subject;
        this.message = b.message;
        this.template = b.template;
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

    public NotificationTemplate getTemplate() {
        return template;
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.EMAIL;
    }

    public static final class Builder {
        private String recipient;
        private String subject;
        private String message;
        private NotificationTemplate template;

        public Builder recipient(String v) {
            this.recipient = v;
            return this;
        }

        public Builder subject(String v) {
            this.subject = v;
            return this;
        }

        public Builder message(String v) {
            this.message = v;
            return this;
        }

        public Builder template(NotificationTemplate v) {
            this.template = v;
            return this;
        }

        public EmailNotification build() {
            return new EmailNotification(this);
        }
    }
}