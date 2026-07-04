package com.example.notifications.examples;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.core.sdk.NotificationSDK;
import com.example.notifications.model.email.EmailNotification;

public class UnifiedExample {

    public static void main(String[] args) {

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(
                                new com.example.notifications.provider.email.SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("demo-key")
                                                .build()
                                )
                        )
                        .defaultFrom("demo@test.com")
                        .build();

        SmsConfiguration smsConfig =
                SmsConfiguration.builder()
                        .provider(
                                new com.example.notifications.provider.sms.TwilioProvider(
                                        new com.example.notifications.config.sms.TwilioConfiguration()
                                )
                        )
                        .build();

        NotificationSDK sdk =
                new NotificationSDK.Builder()
                        .email(emailConfig)
                        .sms(smsConfig)
                        .retryPolicy(new ExponentialBackoffRetryPolicy(3))
                        .circuitBreaker(new CircuitBreaker(3, 5000))
                        .build();

        EmailNotification email =
                EmailNotification.builder()
                        .recipient("user@test.com")
                        .subject("Final SDK Test")
                        .message("Library working correctly")
                        .build();

        sdk.send(email);

        System.out.println("Notification sent successfully");

    }
}