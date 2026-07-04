package com.example.notifications.config.email;

import com.example.notifications.provider.email.EmailProvider;

import java.time.Duration;
import java.util.Objects;

public final class EmailConfiguration {

    private final EmailProvider provider;

    private final String defaultFrom;

    private final Duration timeout;

    private EmailConfiguration(Builder builder) {

        this.provider = Objects.requireNonNull(
                builder.provider,
                "Email provider is required");

        this.defaultFrom = builder.defaultFrom;
        this.timeout = builder.timeout;

    }

    public EmailProvider getProvider() {

        return provider;

    }

    public String getDefaultFrom() {

        return defaultFrom;

    }

    public Duration getTimeout() {

        return timeout;

    }

    public static Builder builder() {

        return new Builder();

    }

    public static final class Builder {

        private EmailProvider provider;

        private String defaultFrom;

        private Duration timeout = Duration.ofSeconds(30);

        public Builder provider(
                EmailProvider provider) {

            this.provider = provider;

            return this;

        }

        public Builder defaultFrom(
                String defaultFrom) {

            this.defaultFrom = defaultFrom;

            return this;

        }

        public Builder timeout(
                Duration timeout) {

            this.timeout = timeout;

            return this;

        }

        public EmailConfiguration build() {

            return new EmailConfiguration(this);

        }

    }

}