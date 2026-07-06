package com.example.notifications.examples;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.push.FirebaseConfiguration;
import com.example.notifications.config.push.PushConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.core.sdk.NotificationSDK;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.push.FirebasePushProvider;
import com.example.notifications.provider.sms.TwilioProvider;

public final class UnifiedExample {

    public static void main(String[] args) {

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(
                                new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("demo")
                                                .build()))
                        .defaultFrom("demo@test.com")
                        .build();

        SmsConfiguration smsConfig =
                SmsConfiguration.builder()
                        .provider(
                                new TwilioProvider(
                                        new TwilioConfiguration()))
                        .build();

        PushConfiguration pushConfig =
                PushConfiguration.builder()
                        .provider(
                                new FirebasePushProvider(
                                        FirebaseConfiguration.builder()
                                                .projectId("demo-project")
                                                .build()))
                        .build();

        try (NotificationSDK sdk =
                     NotificationSDK.builder()
                             .email(emailConfig)
                             .sms(smsConfig)
                             .push(pushConfig)
                             .retryPolicy(
                                     new ExponentialBackoffRetryPolicy(3))
                             .circuitBreaker(
                                     new CircuitBreaker(3, 5000))
                             .build()) {

            NotificationResult result =
                    sdk.send(
                            EmailNotification.builder()
                                    .recipient("user@test.com")
                                    .subject("SDK")
                                    .message("Notification SDK Example")
                                    .build());

            System.out.println(result.getStatus());
            System.out.println(result.getProvider());
            System.out.println(result.getMessageId());

        }

    }

}