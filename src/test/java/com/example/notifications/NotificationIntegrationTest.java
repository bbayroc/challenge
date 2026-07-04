package com.example.notifications;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.model.sms.SmsNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.sms.TwilioProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NotificationIntegrationTest {

    @Test
    void shouldSendEmailAndSmsWithoutErrors() {

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(
                                new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("test")
                                                .build()))
                        .defaultFrom("test@test.com")
                        .build();

        SmsConfiguration smsConfig =
                SmsConfiguration.builder()
                        .provider(
                                new TwilioProvider(
                                        new TwilioConfiguration()))
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
                        .recipient("user@test.com")
                        .subject("Test")
                        .message("Hello")
                        .build();

        SmsNotification sms =
                SmsNotification.builder()
                        .phoneNumber("+519999999")
                        .message("SMS test")
                        .build();

        assertDoesNotThrow(() -> {
            manager.send(email);
            manager.send(sms);
        });
    }

}