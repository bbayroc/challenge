package com.example.notifications;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationAsyncTest {

    @Test
    void shouldSendAsyncSuccessfully() {

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(
                                new com.example.notifications.provider.email.SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("test")
                                                .build()
                                )
                        )
                        .defaultFrom("test@test.com")
                        .build();

        SmsConfiguration smsConfig =
                SmsConfiguration.builder()
                        .provider(
                                new com.example.notifications.provider.sms.TwilioProvider(
                                        new com.example.notifications.config.sms.TwilioConfiguration()
                                )
                        )
                        .build();

        NotificationManager manager =
                NotificationFactory.createManager(
                        emailConfig,
                        smsConfig,
                        new ExponentialBackoffRetryPolicy(3),
                        new CircuitBreaker(3, 5000)
                );

        EmailNotification email =
                EmailNotification.builder()
                        .recipient("test@test.com")
                        .subject("Async test")
                        .message("Hello async")
                        .build();

        assertDoesNotThrow(() -> {

            var future = manager.sendAsync(email);

            NotificationResult result = future.get();

            assertNotNull(result);

        });
    }
}