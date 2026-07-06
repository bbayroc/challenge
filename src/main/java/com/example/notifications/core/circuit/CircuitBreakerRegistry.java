package com.example.notifications.core.circuit;

import com.example.notifications.exception.ConfigurationException;
import com.example.notifications.model.NotificationChannel;

import java.util.EnumMap;
import java.util.Map;

public final class CircuitBreakerRegistry {

    private final Map<
            NotificationChannel,
            CircuitBreaker> registry =
            new EnumMap<>(NotificationChannel.class);

    public CircuitBreakerRegistry register(
            NotificationChannel channel,
            CircuitBreaker breaker) {

        registry.put(channel, breaker);

        return this;

    }

    public CircuitBreaker get(
            NotificationChannel channel) {

        CircuitBreaker breaker =
                registry.get(channel);

        if (breaker == null) {

            throw new ConfigurationException(
                    "No CircuitBreaker registered for channel "
                            + channel);

        }

        return breaker;

    }

}