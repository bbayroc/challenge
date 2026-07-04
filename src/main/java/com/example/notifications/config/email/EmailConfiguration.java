package com.example.notifications.config.email;

import com.example.notifications.config.NotificationContext;
import com.example.notifications.provider.email.EmailProvider;

import java.time.Duration;

public final class EmailConfiguration {

    private final NotificationContext<EmailProvider> context;

    private final String defaultFrom;

    private EmailConfiguration(Builder builder) {

        this.context = new NotificationContext<>(
                builder.provider,
                builder.timeout);

        this.defaultFrom = builder.defaultFrom;

    }

    public EmailProvider getProvider() {

        return context.getProvider();

    }

    public Duration getTimeout() {

        return context.getTimeout();

    }

    public String getDefaultFrom() {

        return defaultFrom;

    }

    public static Builder builder() {

        return new Builder();

    }

    public static final class Builder {

        private EmailProvider provider;

        private Duration timeout =
                Duration.ofSeconds(30);

        private String defaultFrom;

        public Builder provider(
                EmailProvider provider) {

            this.provider = provider;

            return this;

        }

        public Builder timeout(
                Duration timeout) {

            this.timeout = timeout;

            return this;

        }

        public Builder defaultFrom(
                String defaultFrom) {

            this.defaultFrom = defaultFrom;

            return this;

        }

        public EmailConfiguration build() {

            return new EmailConfiguration(this);

        }

    }

}