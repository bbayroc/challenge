package com.example.notifications.validation;

import com.example.notifications.core.validation.NotificationValidator;
import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.sms.SmsNotification;

public final class SmsValidator
        implements NotificationValidator<SmsNotification> {

    @Override
    public NotificationChannel supports() {

        return NotificationChannel.SMS;

    }

    @Override
    public void validate(
            SmsNotification notification) {

        if (notification == null) {

            throw new ValidationException(
                    "Notification cannot be null");

        }

        ValidationSupport.notBlank(
                notification.getPhoneNumber(),
                "Phone number");

        if (!notification.getPhoneNumber().matches(
                "^\\+?[1-9]\\d{7,14}$")) {

            throw new ValidationException(
                    "Invalid phone number");

        }

        if (notification.getTemplate() == null) {

            ValidationSupport.notBlank(
                    notification.getMessage(),
                    "Message");

        }

    }

}