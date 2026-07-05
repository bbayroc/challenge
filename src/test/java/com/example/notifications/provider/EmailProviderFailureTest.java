package com.example.notifications.provider;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.sender.EmailSender;
import com.example.notifications.validation.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailProviderFailureTest {

    @Test
    void shouldReturnFailureResult() {

        EmailSender sender =
                new EmailSender(
                        EmailConfiguration.builder()
                                .provider(
                                        new FailingEmailProvider())
                                .build(),
                        new EmailValidator());

        EmailNotification notification =
                EmailNotification.builder()
                        .recipient("john@example.com")
                        .subject("Failure Test")
                        .message("Body")
                        .build();

        NotificationResult result =
                sender.send(notification);

        assertAll(

                () -> assertEquals(
                        NotificationStatus.FAILED,
                        result.getStatus()),

                () -> assertEquals(
                        "FailingProvider",
                        result.getProvider()),

                () -> assertEquals(
                        500,
                        result.getStatusCode()),

                () -> assertNotNull(
                        result.getTimestamp()),

                () -> assertEquals(
                        "Simulated provider failure",
                        result.getErrorMessage())

        );

    }

}