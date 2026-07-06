package com.example.notifications;

import com.example.notifications.core.NotificationManager;
import com.example.notifications.exception.CircuitBreakerOpenException;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.support.TestAssertions;
import com.example.notifications.support.TestConstants;
import com.example.notifications.support.TestManagerFactory;
import com.example.notifications.support.TestNotificationFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircuitBreakerTest {

    @Test
    void shouldOpenCircuitAfterFailures() {

        try (NotificationManager manager =
                     TestManagerFactory.createWithFailingEmailProvider()) {

            EmailNotification email =
                    TestNotificationFactory.failingEmail();

            NotificationResult r1 =
                    manager.send(email);

            TestAssertions.assertFailed(
                    r1,
                    TestConstants.FAILING_PROVIDER);

            NotificationResult r2 =
                    manager.send(email);

            TestAssertions.assertFailed(
                    r2,
                    TestConstants.FAILING_PROVIDER);

            NotificationResult r3 =
                    manager.send(email);

            TestAssertions.assertFailed(
                    r3,
                    TestConstants.FAILING_PROVIDER);

            CircuitBreakerOpenException ex =
                    assertThrows(
                            CircuitBreakerOpenException.class,
                            () -> manager.send(email));

            assertEquals(
                    "Circuit is OPEN",
                    ex.getMessage());

        }

    }

}