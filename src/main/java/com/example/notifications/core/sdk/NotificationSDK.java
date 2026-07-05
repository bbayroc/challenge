package com.example.notifications.core.sdk;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.EmailChannelHandler;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.PushChannelHandler;
import com.example.notifications.core.SmsChannelHandler;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.RetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.provider.push.FirebasePushProvider;
import com.example.notifications.provider.push.PushProvider;
import com.example.notifications.sender.EmailSender;
import com.example.notifications.sender.SmsSender;
import com.example.notifications.validation.EmailValidator;
import com.example.notifications.validation.SmsValidator;

public final class NotificationSDK
        implements AutoCloseable {

    private final NotificationManager manager;

    private NotificationSDK(Builder builder) {

        EmailSender emailSender =
                new EmailSender(
                        builder.emailConfig,
                        new EmailValidator());

        SmsSender smsSender =
                new SmsSender(
                        builder.smsConfig,
                        new SmsValidator());

        PushProvider pushProvider =
                new FirebasePushProvider();

        this.manager =
                NotificationManager.builder()
                        .handler(
                                new EmailChannelHandler(
                                        emailSender))
                        .handler(
                                new SmsChannelHandler(
                                        smsSender))
                        .handler(
                                new PushChannelHandler(
                                        pushProvider))
                        .retryPolicy(
                                builder.retryPolicy)
                        .circuitBreaker(
                                builder.circuitBreaker)
                        .eventBus(
                                builder.eventBus)
                        .build();

    }

    public NotificationResult send(
            Notification notification) {

        return manager.send(notification);

    }

    public static Builder builder() {

        return new Builder();

    }

    @Override
    public void close() {

        manager.close();

    }

    // =========================
    // BUILDER
    // =========================

    public static final class Builder {

        private EmailConfiguration emailConfig;

        private SmsConfiguration smsConfig;

        private RetryPolicy retryPolicy;

        private CircuitBreaker circuitBreaker;

        private EventBus eventBus =
                new EventBus();

        public Builder email(
                EmailConfiguration emailConfig) {

            this.emailConfig = emailConfig;

            return this;

        }

        public Builder sms(
                SmsConfiguration smsConfig) {

            this.smsConfig = smsConfig;

            return this;

        }

        public Builder retryPolicy(
                RetryPolicy retryPolicy) {

            this.retryPolicy = retryPolicy;

            return this;

        }

        public Builder circuitBreaker(
                CircuitBreaker circuitBreaker) {

            this.circuitBreaker = circuitBreaker;

            return this;

        }

        public Builder eventBus(
                EventBus eventBus) {

            this.eventBus = eventBus;

            return this;

        }

        public NotificationSDK build() {

            if (emailConfig == null) {
                throw new IllegalStateException(
                        "Email configuration is required");
            }

            if (smsConfig == null) {
                throw new IllegalStateException(
                        "SMS configuration is required");
            }

            if (retryPolicy == null) {
                throw new IllegalStateException(
                        "RetryPolicy is required");
            }

            if (circuitBreaker == null) {
                throw new IllegalStateException(
                        "CircuitBreaker is required");
            }

            return new NotificationSDK(this);
        }

    }

}