package com.example.notifications.provider.sms;

import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.sms.SmsNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.time.Instant;


public final class TwilioProvider
        extends AbstractSmsProvider {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(TwilioProvider.class);

    private final TwilioConfiguration configuration;

    public TwilioProvider(
            TwilioConfiguration configuration) {
        super("Twilio");

        this.configuration = configuration;

    }

    @Override
    public NotificationResult send(
            SmsNotification notification){

        LOGGER.info(
                """
                Simulating Twilio SMS delivery
        
                To      : {}
                Message : {}
                """,
                notification.getPhoneNumber(),
                notification.getMessage()
        );

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