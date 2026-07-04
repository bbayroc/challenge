package com.example.notifications.validation;

import com.example.notifications.api.Validator;
import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.sms.SmsNotification;

public final class SmsValidator
        implements Validator<SmsNotification> {

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

        ValidationSupport.notBlank(
                notification.getMessage(),
                "Message");

    }

}