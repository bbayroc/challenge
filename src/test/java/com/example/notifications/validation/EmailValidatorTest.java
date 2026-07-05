package com.example.notifications.validation;

import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.email.EmailNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailValidatorTest {

    private final EmailValidator validator =
            new EmailValidator();

    @Test
    void shouldAcceptValidEmail() {

        EmailNotification notification =
                EmailNotification.builder()
                        .recipient("john@example.com")
                        .subject("Subject")
                        .message("Body")
                        .build();

        assertDoesNotThrow(
                () -> validator.validate(notification));

    }

    @Test
    void shouldRejectInvalidEmail() {

        EmailNotification notification =
                EmailNotification.builder()
                        .recipient("invalid-email")
                        .subject("Subject")
                        .message("Body")
                        .build();

        assertThrows(
                ValidationException.class,
                () -> validator.validate(notification));

    }

}