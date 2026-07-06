package com.example.notifications.validation;

import com.example.notifications.core.validation.NotificationValidator;
import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.push.PushNotification;

public final class PushValidator
        implements NotificationValidator<PushNotification> {

    @Override
    public NotificationChannel supports() {

        return NotificationChannel.PUSH;

    }

    @Override
    public void validate(
            PushNotification notification) {

        if (notification == null) {

            throw new ValidationException(
                    "Notification cannot be null");

        }

        ValidationSupport.notBlank(
                notification.getDeviceToken(),
                "Device token");

        ValidationSupport.notBlank(
                notification.getTitle(),
                "Title");

        if (notification.getTemplate() == null) {

            ValidationSupport.notBlank(
                    notification.getMessage(),
                    "Message");

        }

    }

}