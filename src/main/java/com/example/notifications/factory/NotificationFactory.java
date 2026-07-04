package com.example.notifications.factory;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.sender.EmailSender;
import com.example.notifications.validation.EmailValidator;

public final class NotificationFactory {

    private NotificationFactory() {
    }

    public static EmailSender emailSender(
            EmailConfiguration configuration) {

        return new EmailSender(
                configuration,
                new EmailValidator());

    }

}