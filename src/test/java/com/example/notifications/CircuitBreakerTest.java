package com.example.notifications;

import com.example.notifications.config.email.*;
import com.example.notifications.config.sms.*;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.FailingEmailProvider;
import com.example.notifications.provider.sms.TwilioProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircuitBreakerTest {

    @Test
    void shouldOpenCircuitAfterFailures() {

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(new FailingEmailProvider())
                        .defaultFrom("test@test.com")
                        .build();

        SmsConfiguration smsConfig =
                SmsConfiguration.builder()
                        .provider(new TwilioProvider(new TwilioConfiguration()))
                        .build();

        NotificationManager manager =
                NotificationFactory.createManager(
                        emailConfig,
                        smsConfig,
                        new ExponentialBackoffRetryPolicy(1),
                        new CircuitBreaker(1, 1000)
                );

        EmailNotification email =
                EmailNotification.builder()
                        .recipient("test@test.com")
                        .subject("fail")
                        .message("fail")
                        .build();

        // 1st call → failure recorded
        NotificationResult r1 = manager.send(email);
        assertEquals("FAILED", r1.getStatus().name());

        // 2nd call → circuit must block
        Exception ex = assertThrows(
                IllegalStateException.class,
                () -> manager.send(email)
        );

        assertTrue(ex.getMessage().contains("Circuit is OPEN"));
    }

}