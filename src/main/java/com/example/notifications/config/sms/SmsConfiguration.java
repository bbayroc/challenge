package com.example.notifications.config.sms;

import com.example.notifications.config.NotificationContext;
import com.example.notifications.provider.sms.SmsProvider;

import java.time.Duration;

public final class SmsConfiguration {

    private final NotificationContext<SmsProvider> context;

    private SmsConfiguration(Builder builder) {

        this.context = new NotificationContext<>(
                builder.provider,
                builder.timeout);

    }

    public SmsProvider getProvider() {

        return context.getProvider();

    }

    public Duration getTimeout() {

        return context.getTimeout();

    }

    public static Builder builder() {

        return new Builder();

    }

    public static final class Builder {

        private SmsProvider provider;

        private Duration timeout =
                Duration.ofSeconds(30);

        public Builder provider(
                SmsProvider provider) {

            this.provider = provider;

            return this;

        }

        public Builder timeout(
                Duration timeout) {

            this.timeout = timeout;

            return this;

        }

        public SmsConfiguration build() {

            return new SmsConfiguration(this);

        }

    }

}