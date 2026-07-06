package com.example.notifications;

import com.example.notifications.core.NotificationManager;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.support.TestAssertions;
import com.example.notifications.support.TestConstants;
import com.example.notifications.support.TestSupport;
import org.junit.jupiter.api.Test;

class PushNotificationTest {

    @Test
    void shouldSendPushNotificationSuccessfully() {

        try (NotificationManager manager =
                     TestSupport.manager()) {

            NotificationResult result =
                    manager.send(
                            TestSupport.push());

            TestAssertions.assertSuccessful(
                    result,
                    TestConstants.PUSH_PROVIDER);

        }

    }

}