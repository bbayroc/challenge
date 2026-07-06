package com.example.notifications.provider.push;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.push.PushNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public final class OneSignalPushProvider
        implements PushProvider {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    OneSignalPushProvider.class);

    @Override
    public NotificationResult send(
            PushNotification notification) {

        LOGGER.info(
                """
                Simulating OneSignal push delivery

                Device : {}
                Title  : {}
                Message: {}
                """,
                notification.getDeviceToken(),
                notification.getTitle(),
                notification.getMessage());

        return new NotificationResult(
                NotificationStatus.SUCCESS,
                getProviderName(),
                UUID.randomUUID().toString(),
                null,
                200,
                Duration.ofMillis(75),
                Instant.now());

    }

    @Override
    public String getProviderName() {

        return "OneSignal";

    }

}