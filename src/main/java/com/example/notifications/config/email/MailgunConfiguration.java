package com.example.notifications.config.email;

import com.example.notifications.config.ProviderConfiguration;

import java.time.Duration;

public final class MailgunConfiguration extends ProviderConfiguration {

    private final String apiKey;

    private final Duration timeout;

    private MailgunConfiguration(Builder builder) {

        super("Mailgun");

        this.apiKey = builder.apiKey;

        this.timeout = builder.timeout;

    }

    public String getApiKey() {

        return apiKey;

    }

    public Duration getTimeout() {

        return timeout;

    }

    public static Builder builder() {

        return new Builder();

    }

    public static final class Builder {

        private String apiKey;

        private Duration timeout = Duration.ofSeconds(30);

        public Builder apiKey(String apiKey) {

            this.apiKey = apiKey;

            return this;

        }

        public Builder timeout(Duration timeout) {

            this.timeout = timeout;

            return this;

        }

        public MailgunConfiguration build() {

            return new MailgunConfiguration(this);

        }

    }

}