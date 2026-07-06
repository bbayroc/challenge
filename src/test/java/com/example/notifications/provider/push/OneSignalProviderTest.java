package com.example.notifications.provider.push;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.support.TestAssertions;
import com.example.notifications.support.TestNotificationFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneSignalProviderTest {

    @Test
    void shouldSendPushUsingOneSignal() {

        OneSignalPushProvider provider =
                new OneSignalPushProvider();

        NotificationResult result =
                provider.send(
                        TestNotificationFactory.push());

        TestAssertions.assertSuccessful(
                result,
                "OneSignal");

        assertEquals(
                200,
                result.getStatusCode());

    }

}