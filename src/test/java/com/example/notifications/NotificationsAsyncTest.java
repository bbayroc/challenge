package com.example.notifications;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.sms.TwilioProvider;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class NotificationAsyncTest {

    @Test
    void shouldSendNotificationAsynchronously() throws Exception {

        NotificationManager manager =
                NotificationFactory.createManager(
                        EmailConfiguration.builder()
                                .provider(new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("test")
                                                .build()))
                                .defaultFrom("test@test.com")
                                .build(),

                        SmsConfiguration.builder()
                                .provider(new TwilioProvider(
                                        new TwilioConfiguration()))
                                .build(),

                        new ExponentialBackoffRetryPolicy(3),
                        new CircuitBreaker(3,5000),
                        new EventBus()
                );

        EmailNotification email =
                EmailNotification.builder()
                        .recipient("john@test.com")
                        .subject("Async")
                        .message("Hello")
                        .build();

        NotificationResult result =
                manager.sendAsync(email)
                        .get(5, TimeUnit.SECONDS);

        assertNotNull(result);
        assertEquals(NotificationStatus.SUCCESS, result.getStatus());
    }
}