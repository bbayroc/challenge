package com.example.notifications.examples;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.sender.EmailSender;

public final class EmailExample {

    public static void main(String[] args) {

        EmailConfiguration configuration =
                EmailConfiguration.builder()
                        .provider(new SendGridProvider())
                        .defaultFrom("noreply@example.com")
                        .build();

        EmailSender sender =
                NotificationFactory.emailSender(
                        configuration);

        EmailNotification notification =
                EmailNotification.builder()
                        .recipient("john.doe@example.com")
                        .subject("Welcome")
                        .message(
                                "Welcome to the notification library!")
                        .build();

        NotificationResult result =
                sender.send(notification);

        System.out.println();
        System.out.println(
                "===== Notification Result =====");

        System.out.println(
                "Notification Id : "
                        + notification.getId());

        System.out.println(
                "Status          : "
                        + result.getStatus());

        System.out.println(
                "Provider        : "
                        + result.getProvider());

        System.out.println(
                "Message Id      : "
                        + result.getMessageId());

        System.out.println(
                "Status Code     : "
                        + result.getStatusCode());

        System.out.println(
                "Duration        : "
                        + result.getDuration());

        System.out.println(
                "Timestamp       : "
                        + result.getTimestamp());

    }

}