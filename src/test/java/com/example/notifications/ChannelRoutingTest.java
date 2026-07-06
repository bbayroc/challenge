package com.example.notifications;

import com.example.notifications.core.NotificationManager;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.support.TestAssertions;
import com.example.notifications.support.TestConstants;
import com.example.notifications.support.TestSupport;
import org.junit.jupiter.api.Test;

class ChannelRoutingTest {

    @Test
    void shouldRouteEachNotificationToItsChannel() {

        try (NotificationManager manager =
                     TestSupport.manager()) {

            NotificationResult emailResult =
                    manager.send(TestSupport.email());

            NotificationResult smsResult =
                    manager.send(TestSupport.sms());

            NotificationResult pushResult =
                    manager.send(TestSupport.push());

            TestAssertions.assertSuccessful(
                    emailResult,
                    TestConstants.EMAIL_PROVIDER);

            TestAssertions.assertSuccessful(
                    smsResult,
                    TestConstants.SMS_PROVIDER);

            TestAssertions.assertSuccessful(
                    pushResult,
                    TestConstants.PUSH_PROVIDER);

        }

    }

}