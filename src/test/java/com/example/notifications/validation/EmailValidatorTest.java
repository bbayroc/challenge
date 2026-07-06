package com.example.notifications.validation;

import com.example.notifications.exception.ValidationException;
import com.example.notifications.support.TestNotificationFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private final EmailValidator validator =
            new EmailValidator();

    @Test
    void shouldAcceptValidEmail() {

        assertDoesNotThrow(
                () -> validator.validate(
                        TestNotificationFactory.email()));

    }

    @Test
    void shouldRejectInvalidEmail() {

        assertThrows(
                ValidationException.class,
                () -> validator.validate(
                        TestNotificationFactory.invalidEmail()));

    }

}