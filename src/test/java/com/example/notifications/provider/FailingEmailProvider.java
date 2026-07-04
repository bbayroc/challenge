package com.example.notifications.provider;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.AbstractEmailProvider;

import java.time.Duration;
import java.time.Instant;

public final class FailingEmailProvider
        extends AbstractEmailProvider {

    public FailingEmailProvider() {
        super("FailingProvider");
    }

    @Override
    public NotificationResult send(
            EmailNotification notification) {

        return new NotificationResult(
                NotificationStatus.FAILED,
                getProviderName(),
                null,
                "Simulated provider failure",
                500,
                Duration.ZERO,
                Instant.now()
        );

    }

}