package com.example.notifications.examples;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.FixedRetryPolicy;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.sms.TwilioProvider;

public final class NotificationExample {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Notification Library Demo");
        System.out.println("=================================");

        SendGridConfiguration sendGridConfig =
                SendGridConfiguration.builder()
                        .apiKey("demo-api-key")
                        .build();

        EmailConfiguration emailConfiguration =
                EmailConfiguration.builder()
                        .provider(
                                new SendGridProvider(sendGridConfig))
                        .defaultFrom("noreply@example.com")
                        .build();

        SmsConfiguration smsConfiguration =
                SmsConfiguration.builder()
                        .provider(
                                new TwilioProvider(
                                        new TwilioConfiguration()))
                        .build();

        NotificationManager manager =
                NotificationFactory.createManager(
                        emailConfiguration,
                        smsConfiguration,
                        new FixedRetryPolicy(
                                3,
                                1000),
                        new CircuitBreaker(
                                3,
                                5000)
                );

        EmailNotification notification =
                EmailNotification.builder()
                        .recipient("john@example.com")
                        .subject("Welcome!")
                        .message("Hello John, welcome to the library.")
                        .build();

        NotificationResult result =
                manager.send(notification);

        System.out.println();
        System.out.println("===== RESULT =====");
        System.out.println("Status      : " + result.getStatus());
        System.out.println("Provider    : " + result.getProvider());
        System.out.println("Message Id  : " + result.getMessageId());
        System.out.println("Timestamp   : " + result.getTimestamp());
    }

}