package com.example.notifications.core.sdk;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.ChannelResolver;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.execution.ExecutionContext;
import com.example.notifications.core.retry.RetryPolicy;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;

public final class NotificationSDK {

    private final NotificationManager manager;

    private NotificationSDK(Builder builder) {

        this.manager =
                NotificationFactory.createManager(
                        builder.emailConfig,
                        builder.smsConfig,
                        builder.retryPolicy,
                        builder.circuitBreaker
                );

    }

    public NotificationResult send(Notification notification) {

        return manager.send(notification);

    }

    public Builder builder() {

        return new Builder();

    }

    // =========================
    // BUILDER
    // =========================

    public static final class Builder {

        private EmailConfiguration emailConfig;

        private SmsConfiguration smsConfig;

        private RetryPolicy retryPolicy;

        private CircuitBreaker circuitBreaker;

        public Builder email(EmailConfiguration emailConfig) {

            this.emailConfig = emailConfig;

            return this;

        }

        public Builder sms(SmsConfiguration smsConfig) {

            this.smsConfig = smsConfig;

            return this;

        }

        public Builder retryPolicy(RetryPolicy retryPolicy) {

            this.retryPolicy = retryPolicy;

            return this;

        }

        public Builder circuitBreaker(CircuitBreaker circuitBreaker) {

            this.circuitBreaker = circuitBreaker;

            return this;

        }

        public NotificationSDK build() {

            if (emailConfig == null || smsConfig == null) {

                throw new IllegalStateException(
                        "Email and SMS configuration are required");

            }

            return new NotificationSDK(this);

        }

    }
}