package com.example.notifications.model.sms;

import com.example.notifications.model.*;
import com.example.notifications.template.NotificationTemplate;

public final class SmsNotification implements Notification {
    private final String phoneNumber;
    private final String message;
    private final NotificationTemplate template;

    private SmsNotification(Builder b) {
        this.phoneNumber = b.phoneNumber;
        this.message = b.message;
        this.template = b.template;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public NotificationTemplate getTemplate() {
        return template;
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.SMS;
    }

    public static final class Builder {
        private String phoneNumber;
        private String message;
        private NotificationTemplate template;

        public Builder phoneNumber(String v) {
            this.phoneNumber = v;
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

        public SmsNotification build() {
            return new SmsNotification(this);
        }
    }
}