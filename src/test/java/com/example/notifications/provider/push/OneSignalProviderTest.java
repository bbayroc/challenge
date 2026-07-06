package com.example.notifications.provider.push;

import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.push.PushNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneSignalProviderTest {

    @Test
    void shouldSendPushUsingOneSignal() {

        OneSignalPushProvider provider =
                new OneSignalPushProvider();

        PushNotification notification =
                PushNotification.builder()
                        .deviceToken("device-1")
                        .title("Hello")
                        .message("Testing OneSignal")
                        .build();

        var result =
                provider.send(notification);

        assertAll(

                () -> assertEquals(
                        NotificationStatus.SUCCESS,
                        result.getStatus()),

                () -> assertEquals(
                        "OneSignal",
                        result.getProvider()),

                () -> assertNotNull(
                        result.getMessageId()),

                () -> assertEquals(
                        200,
                        result.getStatusCode())

        );

    }

}