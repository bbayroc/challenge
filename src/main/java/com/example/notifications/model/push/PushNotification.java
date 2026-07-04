package com.example.notifications.model.push;

import com.example.notifications.model.*;
import com.example.notifications.template.NotificationTemplate;

public final class PushNotification implements Notification {
    private final String deviceToken;
    private final String title;
    private final String message;
    private final NotificationTemplate template;

    private PushNotification(Builder b) {
        this.deviceToken = b.deviceToken;
        this.title = b.title;
        this.message = b.message;
        this.template = b.template;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public NotificationTemplate getTemplate() {
        return template;
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.PUSH;
    }

    public static final class Builder {
        private String deviceToken;
        private String title;
        private String message;
        private NotificationTemplate template;

        public Builder deviceToken(String v) {
            this.deviceToken = v;
            return this;
        }

        public Builder title(String v) {
            this.title = v;
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

        public PushNotification build() {
            return new PushNotification(this);
        }
    }
}