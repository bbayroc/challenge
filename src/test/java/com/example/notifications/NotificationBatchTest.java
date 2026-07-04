package com.example.notifications;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.Notification;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.model.sms.SmsNotification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationBatchTest {

    @Test
    void shouldSendBatchSuccessfully() {

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

        List<Notification> batch = List.of(
                EmailNotification.builder()
                        .recipient("a@test.com")
                        .subject("A")
                        .message("A")
                        .build(),

                SmsNotification.builder()
                        .phoneNumber("+519999")
                        .message("B")
                        .build(),

                PushNotification.builder()
                        .deviceToken("device-1")
                        .title("C")
                        .message("C")
                        .build()
        );

        assertDoesNotThrow(() -> {

            var results = manager.sendBatch(batch);

            assertEquals(3, results.size());

        });
    }
}