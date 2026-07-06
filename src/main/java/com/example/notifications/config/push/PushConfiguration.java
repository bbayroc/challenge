package com.example.notifications.config.push;

import com.example.notifications.provider.push.PushProvider;

import java.util.Objects;

public final class PushConfiguration {

    private final PushProvider provider;

    private PushConfiguration(Builder builder) {

        this.provider =
                Objects.requireNonNull(
                        builder.provider,
                        "PushProvider is required");

    }

    public PushProvider getProvider() {

        return provider;

    }

    public static Builder builder() {

        return new Builder();

    }

    public static final class Builder {

        private PushProvider provider;

        public Builder provider(
                PushProvider provider) {

            this.provider = provider;

            return this;

        }

        public PushConfiguration build() {

            return new PushConfiguration(this);

        }

    }

}