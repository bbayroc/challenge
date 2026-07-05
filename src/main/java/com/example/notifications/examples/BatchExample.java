package com.example.notifications.examples;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.model.sms.SmsNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.sms.TwilioProvider;

import java.util.List;

public final class BatchExample {

    public static void main(String[] args) {

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(
                                new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("demo")
                                                .build()))
                        .defaultFrom("demo@example.com")
                        .build();

        SmsConfiguration smsConfig =
                SmsConfiguration.builder()
                        .provider(
                                new TwilioProvider(
                                        new TwilioConfiguration()))
                        .build();

        try (NotificationManager manager =
                     NotificationFactory.createManager(
                             emailConfig,
                             smsConfig,
                             new ExponentialBackoffRetryPolicy(3),
                             new CircuitBreaker(3, 5000),
                             new EventBus())) {

            List<Notification> notifications =
                    List.of(

                            EmailNotification.builder()
                                    .recipient("alice@example.com")
                                    .subject("Welcome")
                                    .message("Welcome Alice!")
                                    .build(),

                            SmsNotification.builder()
                                    .phoneNumber("+51999999999")
                                    .message("Verification code: 123456")
                                    .build(),

                            PushNotification.builder()
                                    .deviceToken("device-token-001")
                                    .title("New Promotion")
                                    .message("Check today's offers!")
                                    .build(),

                            EmailNotification.builder()
                                    .recipient("bob@example.com")
                                    .subject("Order")
                                    .message("Your order has shipped.")
                                    .build()

                    );

            List<NotificationResult> results =
                    manager.sendBatch(notifications);

            System.out.println("Batch Results");
            System.out.println("----------------------------");

            results.forEach(result -> {

                System.out.println(
                        result.getProvider()
                                + " -> "
                                + result.getStatus()
                                + " ("
                                + result.getMessageId()
                                + ")");

            });

            System.out.println();
            System.out.println("Executing asynchronous batch...");

            List<NotificationResult> asyncResults =
                    manager.sendBatchAsync(notifications)
                            .join();

            System.out.println(
                    "Async batch completed: "
                            + asyncResults.size()
                            + " notifications processed.");

        }

    }

}