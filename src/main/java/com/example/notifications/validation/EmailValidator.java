package com.example.notifications.validation;

import com.example.notifications.api.Validator;
import com.example.notifications.exception.ValidationException;
import com.example.notifications.model.email.EmailNotification;

public final class EmailValidator
        implements Validator<EmailNotification> {

    @Override
    public void validate(
            EmailNotification notification) {

        if (notification == null) {

            throw new ValidationException(
                    "Notification cannot be null");

        }

        ValidationSupport.notBlank(
                notification.getRecipient(),
                "Recipient");

        ValidationSupport.notBlank(
                notification.getSubject(),
                "Subject");

        ValidationSupport.notBlank(
                notification.getMessage(),
                "Message");

        if (!ValidationUtils.isValidEmail(
                notification.getRecipient())) {

            throw new ValidationException(
                    "Recipient email is invalid");

        }

    }

}