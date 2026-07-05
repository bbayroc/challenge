package com.example.notifications;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.core.sdk.NotificationSDK;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.sms.TwilioProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationSDKTest {

    @Test
    void shouldSendNotificationUsingSdk() {

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(
                                new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("demo")
                                                .build()))
                        .defaultFrom("test@test.com")
                        .build();

        SmsConfiguration smsConfig =
                SmsConfiguration.builder()
                        .provider(
                                new TwilioProvider(
                                        new TwilioConfiguration()))
                        .build();

        try(NotificationSDK sdk =
                    NotificationSDK.builder()
                            .email(emailConfig)
                            .sms(smsConfig)
                            .retryPolicy(
                                    new ExponentialBackoffRetryPolicy(3))
                            .circuitBreaker(
                                    new CircuitBreaker(3,5000))
                            .build()) {

            NotificationResult result =
                    sdk.send(
                            EmailNotification.builder()
                                    .recipient("user@test.com")
                                    .subject("SDK")
                                    .message("Hello")
                                    .build());

            assertNotNull(result);
            assertNotNull(result.getStatus());

        }

    }

}