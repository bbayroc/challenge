package com.example.notifications;

import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.PushChannelHandler;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.FixedRetryPolicy;
import com.example.notifications.provider.push.FirebasePushProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationManagerBuilderTest {

    @Test
    void shouldRejectMissingRetryPolicy() {

        IllegalStateException ex =
                assertThrows(
                        IllegalStateException.class,
                        () ->
                                NotificationManager.builder()
                                        .handler(
                                                new PushChannelHandler(
                                                        new FirebasePushProvider()))
                                        .circuitBreaker(
                                                new CircuitBreaker(3,5000))
                                        .build());

        assertTrue(
                ex.getMessage().contains("RetryPolicy"));

    }

    @Test
    void shouldRejectMissingCircuitBreaker() {

        IllegalStateException ex =
                assertThrows(
                        IllegalStateException.class,
                        () ->
                                NotificationManager.builder()
                                        .handler(
                                                new PushChannelHandler(
                                                        new FirebasePushProvider()))
                                        .retryPolicy(
                                                new FixedRetryPolicy(3,500))
                                        .build());

        assertTrue(
                ex.getMessage().contains("CircuitBreaker"));

    }

    @Test
    void shouldRejectMissingHandlers() {

        IllegalStateException ex =
                assertThrows(
                        IllegalStateException.class,
                        () ->
                                NotificationManager.builder()
                                        .retryPolicy(
                                                new FixedRetryPolicy(3,500))
                                        .circuitBreaker(
                                                new CircuitBreaker(3,5000))
                                        .build());

        assertTrue(
                ex.getMessage().contains("ChannelHandler"));

    }

}