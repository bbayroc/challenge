package com.example.notifications.provider.email;

import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.email.EmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public final class SendGridProvider extends AbstractEmailProvider {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SendGridProvider.class);

    private final SendGridConfiguration configuration;

    public SendGridProvider(
            SendGridConfiguration configuration) {

        this.configuration = configuration;

    }

    @Override
    public NotificationResult send(
            EmailNotification notification) {

        LOGGER.info(
                "Simulating SendGrid email delivery to {}",
                notification.getRecipient());

        return new NotificationResult(
                NotificationStatus.SUCCESS,
                getProviderName(),
                "SIMULATED-ID",
                null,
                200,
                Duration.ZERO,
                Instant.now()
        );

    }

}