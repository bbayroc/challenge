package com.example.notifications.validation;

import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.email.EmailNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailValidatorTest {

    private final EmailValidator validator =
            new EmailValidator();

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