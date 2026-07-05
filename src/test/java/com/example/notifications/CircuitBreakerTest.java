package com.example.notifications;

import com.example.notifications.config.email.*;
import com.example.notifications.config.sms.*;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.event.EventBus;
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

        EventBus eventBus = new EventBus();

        NotificationManager manager =
                NotificationFactory.createManager(
                        emailConfig,
                        smsConfig,
                        new ExponentialBackoffRetryPolicy(3),
                        new CircuitBreaker(3, 5000),
                        eventBus
                );

        EmailNotification email =
                EmailNotification.builder()
                        .recipient("test@test.com")
                        .subject("fail")
                        .message("fail")
                        .build();

// First failure
        NotificationResult r1 = manager.send(email);
        assertEquals("FAILED", r1.getStatus().name());

// Second failure
        NotificationResult r2 = manager.send(email);
        assertEquals("FAILED", r2.getStatus().name());

// Third failure (this opens the circuit)
        NotificationResult r3 = manager.send(email);
        assertEquals("FAILED", r3.getStatus().name());

// Fourth request must be rejected
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> manager.send(email)
        );

        assertEquals("Circuit is OPEN", ex.getMessage());
    }

}