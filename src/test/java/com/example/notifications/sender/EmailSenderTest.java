package com.example.notifications.sender;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.validation.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailSenderTest {

    @Test
    void shouldSendEmailSuccessfully() {

        EmailSender sender =
                new EmailSender(
                        EmailConfiguration.builder()
                                .provider(new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("test")
                                                .build()))
                                .build(),
                        new EmailValidator());

        EmailNotification notification =
                EmailNotification.builder()
                        .recipient("john@example.com")
                        .subject("Test")
                        .message("Hello")
                        .build();

        var result = sender.send(notification);

        assertEquals(
                NotificationStatus.SUCCESS,
                result.getStatus());

        assertEquals(
                "SendGridProvider",
                result.getProvider());

        assertNotNull(
                result.getMessageId());

    }

}