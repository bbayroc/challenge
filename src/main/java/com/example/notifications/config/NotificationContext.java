package com.example.notifications.config;

import java.time.Duration;
import java.util.Objects;

public final class NotificationContext<T> {

    private final T provider;

    private final Duration timeout;

    public NotificationContext(
            T provider,
            Duration timeout) {

        this.provider = Objects.requireNonNull(provider);

        this.timeout = timeout;

    }

    public T getProvider() {

        return provider;

    }

    public Duration getTimeout() {

        return timeout;

    }

}