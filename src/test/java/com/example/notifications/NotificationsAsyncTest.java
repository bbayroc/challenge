package com.example.notifications;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.support.TestAssertions;
import com.example.notifications.support.TestConstants;
import com.example.notifications.support.TestSupport;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class NotificationAsyncTest {

    @Test
    void shouldSendNotificationAsynchronously() throws Exception {

        try (var manager =
                     TestSupport.asyncManager()) {

            NotificationResult result =
                    manager.sendAsync(
                                    TestSupport.asyncEmail())
                            .get(
                                    5,
                                    TimeUnit.SECONDS);

            TestAssertions.assertSuccessful(
                    result,
                    TestConstants.EMAIL_PROVIDER);

        }

    }

}