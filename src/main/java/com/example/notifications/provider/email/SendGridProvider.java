package com.example.notifications.provider.email;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.email.EmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public final class SendGridProvider extends AbstractEmailProvider {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SendGridProvider.class);

    public SendGridProvider() {
        super("SendGrid");
    }

    @Override
    public NotificationResult send(EmailNotification notification) {

        LOGGER.info(
                "Simulating SendGrid email delivery to {}",
                notification.getRecipient());

        return new NotificationResult(
                NotificationStatus.SUCCESS,
                getProviderName(),
                UUID.randomUUID().toString(),
                null,
                202,
                Duration.ofMillis(125),
                Instant.now()
        );
    }

}