package com.example.notifications.validation;

import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.sms.SmsNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SmsValidatorTest {

    private final SmsValidator validator =
            new SmsValidator();

    @Test
    void shouldAcceptValidPhoneNumber() {

        SmsNotification notification =
                SmsNotification.builder()
                        .phoneNumber("+51987654321")
                        .message("Hello")
                        .build();

        assertDoesNotThrow(
                () -> validator.validate(notification));

    }

    @Test
    void shouldRejectInvalidPhoneNumber() {

        SmsNotification notification =
                SmsNotification.builder()
                        .phoneNumber("invalid-phone")
                        .message("Hello")
                        .build();

        assertThrows(
                ValidationException.class,
                () -> validator.validate(notification));

    }

}