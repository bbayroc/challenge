package com.example.notifications.validation;

import com.example.notifications.exception.ValidationException;
import com.example.notifications.support.TestNotificationFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmsValidatorTest {

    private final SmsValidator validator =
            new SmsValidator();

    @Test
    void shouldAcceptValidPhoneNumber() {

        assertDoesNotThrow(
                () -> validator.validate(
                        TestNotificationFactory.sms()));

    }

    @Test
    void shouldRejectInvalidPhoneNumber() {

        assertThrows(
                ValidationException.class,
                () -> validator.validate(
                        TestNotificationFactory.invalidSms()));

    }

}