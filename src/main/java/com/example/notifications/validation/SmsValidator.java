package com.example.notifications.validation;

import com.example.notifications.api.Validator;
import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.sms.SmsNotification;

public final class SmsValidator
        implements Validator<SmsNotification> {

    private static final String PHONE_REGEX =
            "^\\+[1-9]\\d{7,14}$";

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

        if (!notification.getPhoneNumber()
                .matches(PHONE_REGEX)) {

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