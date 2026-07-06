package com.example.notifications.validation;

import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.push.PushNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushValidatorTest {

    private final PushValidator validator =
            new PushValidator();

    @Test
    void shouldAcceptValidPushNotification() {

        PushNotification notification =
                PushNotification.builder()
                        .deviceToken("device-123")
                        .title("Welcome")
                        .message("Hello")
                        .build();

        assertDoesNotThrow(
                () -> validator.validate(notification));

    }

    @Test
    void shouldRejectNullNotification() {

        assertThrows(
                ValidationException.class,
                () -> validator.validate(null));

    }

    @Test
    void shouldRejectMissingDeviceToken() {

        PushNotification notification =
                PushNotification.builder()
                        .deviceToken("")
                        .title("Welcome")
                        .message("Hello")
                        .build();

        assertThrows(
                ValidationException.class,
                () -> validator.validate(notification));

    }

    @Test
    void shouldRejectMissingTitle() {

        PushNotification notification =
                PushNotification.builder()
                        .deviceToken("device-123")
                        .title("")
                        .message("Hello")
                        .build();

        assertThrows(
                ValidationException.class,
                () -> validator.validate(notification));

    }

    @Test
    void shouldRejectMissingMessageWhenNoTemplateExists() {

        PushNotification notification =
                PushNotification.builder()
                        .deviceToken("device-123")
                        .title("Welcome")
                        .message("")
                        .build();

        assertThrows(
                ValidationException.class,
                () -> validator.validate(notification));

    }

}