package com.example.notifications;

import com.example.notifications.core.NotificationManager;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.support.TestAssertions;
import com.example.notifications.support.TestConstants;
import com.example.notifications.support.TestSupport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationBatchTest {

    @Test
    void shouldSendBatchSuccessfully() {

        try (NotificationManager manager =
                     TestSupport.manager()) {

            List<Notification> notifications =
                    List.of(

                            TestSupport.email(),

                            TestSupport.sms(),

                            TestSupport.push()

                    );

            List<NotificationResult> results =
                    manager.sendBatch(
                            notifications);

            assertEquals(
                    3,
                    results.size());

            TestAssertions.assertSuccessful(
                    results.get(0),
                    TestConstants.EMAIL_PROVIDER);

            TestAssertions.assertSuccessful(
                    results.get(1),
                    TestConstants.SMS_PROVIDER);

            TestAssertions.assertSuccessful(
                    results.get(2),
                    TestConstants.PUSH_PROVIDER);

        }

    }

}