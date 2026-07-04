package com.example.notifications.provider.email;

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

    public MailgunProvider() {
        super("Mailgun");
    }

    @Override
    public NotificationResult send(EmailNotification notification) {

        LOGGER.info(
                "Simulating Mailgun email delivery to {}",
                notification.getRecipient());

        return new NotificationResult(
                NotificationStatus.SUCCESS,
                getProviderName(),
                UUID.randomUUID().toString(),
                null,
                202,
                Duration.ofMillis(145),
                Instant.now()
        );
    }

}