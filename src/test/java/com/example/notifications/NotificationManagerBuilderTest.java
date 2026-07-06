package com.example.notifications;

import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.PushChannelHandler;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.FixedRetryPolicy;
import com.example.notifications.exception.ConfigurationException;
import com.example.notifications.sender.PushSender;
import com.example.notifications.support.TestConfigurationFactory;
import com.example.notifications.validation.PushValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationManagerBuilderTest {

    private PushChannelHandler createPushHandler() {

        PushSender sender =
                new PushSender(
                        TestConfigurationFactory.push(),
                        new PushValidator());

        return new PushChannelHandler(sender);

    }

    @Test
    void shouldRejectMissingRetryPolicy() {

        ConfigurationException ex =
                assertThrows(
                        ConfigurationException.class,
                        () ->
                                NotificationManager.builder()
                                        .handler(createPushHandler())
                                        .circuitBreaker(
                                                new CircuitBreaker(3, 5000))
                                        .build());

        assertTrue(
                ex.getMessage().contains("RetryPolicy"));

    }

    @Test
    void shouldRejectMissingCircuitBreaker() {

        ConfigurationException ex =
                assertThrows(
                        ConfigurationException.class,
                        () ->
                                NotificationManager.builder()
                                        .handler(createPushHandler())
                                        .retryPolicy(
                                                new FixedRetryPolicy(3, 500))
                                        .build());

        assertTrue(
                ex.getMessage().contains("CircuitBreaker"));

    }

    @Test
    void shouldRejectMissingHandlers() {

        ConfigurationException ex =
                assertThrows(
                        ConfigurationException.class,
                        () ->
                                NotificationManager.builder()
                                        .retryPolicy(
                                                new FixedRetryPolicy(3, 500))
                                        .circuitBreaker(
                                                new CircuitBreaker(3, 5000))
                                        .build());

        assertTrue(
                ex.getMessage().contains("ChannelHandler"));

    }

    @Test
    void shouldBuildManagerSuccessfully() {

        try (NotificationManager manager =
                     NotificationManager.builder()
                             .handler(createPushHandler())
                             .retryPolicy(
                                     new FixedRetryPolicy(3, 500))
                             .circuitBreaker(
                                     new CircuitBreaker(3, 5000))
                             .build()) {

            assertNotNull(manager);

        }

    }

}