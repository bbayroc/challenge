package com.example.notifications.provider.push;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.push.PushNotification;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public final class FirebasePushProvider
        implements PushProvider {

    @Override
    public NotificationResult send(
            PushNotification notification) {

        return new NotificationResult(
                NotificationStatus.SUCCESS,
                "Firebase",
                UUID.randomUUID().toString(),
                null,
                200,
                Duration.ofMillis(80),
                Instant.now()
        );

    }

    @Override
    public String getProviderName() {
        return "Firebase";
    }

}