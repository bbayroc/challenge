package com.example.notifications.core.execution;

import com.example.notifications.api.Validator;
import com.example.notifications.model.Notification;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.model.sms.SmsNotification;
import com.example.notifications.validation.EmailValidator;
import com.example.notifications.validation.SmsValidator;

public final class ValidationExecutor {

    private final Validator<EmailNotification> emailValidator =
            new EmailValidator();

    private final Validator<SmsNotification> smsValidator =
            new SmsValidator();

    public void validate(Notification notification) {

        if (notification instanceof EmailNotification) {

            emailValidator.validate(
                    (EmailNotification) notification);

            return;

        }

        if (notification instanceof SmsNotification) {

            smsValidator.validate(
                    (SmsNotification) notification);

            return;

        }

        if (notification instanceof PushNotification) {

            return;

        }

        throw new IllegalArgumentException(
                "Unsupported notification type: "
                        + notification.getClass().getSimpleName());

    }

}