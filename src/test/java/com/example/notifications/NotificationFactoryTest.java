package com.example.notifications;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.FixedRetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.sms.TwilioProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationFactoryTest {

    @Test
    void shouldCreateNotificationManager() {

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(
                                new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("test")
                                                .build()))
                        .defaultFrom("demo@test.com")
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
                        new FixedRetryPolicy(3,500),
                        new CircuitBreaker(3,5000),
                        new EventBus()
                );

        assertNotNull(manager);
    }

}