package com.example.notifications.provider.email;

import com.example.notifications.config.email.MailgunConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.email.EmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public final class MailgunProvider extends AbstractEmailProvider {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MailgunProvider.class);

    private final MailgunConfiguration configuration;

    public MailgunProvider(
            MailgunConfiguration configuration) {
        super("Mailgun");

        this.configuration = configuration;

    }

    @Override
    public NotificationResult send(
            EmailNotification notification) {

        LOGGER.info(
                "Simulating Mailgun email delivery to {}",
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