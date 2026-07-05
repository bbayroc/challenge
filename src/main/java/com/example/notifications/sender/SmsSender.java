package com.example.notifications.sender;

import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.sms.SmsNotification;
import com.example.notifications.template.SimpleTemplateEngine;
import com.example.notifications.template.TemplateEngine;
import com.example.notifications.template.TemplateRenderer;
import com.example.notifications.validation.SmsValidator;

public final class SmsSender {

    private final SmsConfiguration configuration;

    private final SmsValidator validator;

    private final TemplateRenderer templateRenderer;

    public SmsSender(
            SmsConfiguration configuration,
            SmsValidator validator) {

        this.configuration = configuration;
        this.validator = validator;
        this.templateRenderer =
                new TemplateRenderer();

    }

    public NotificationResult send(
            SmsNotification notification) {

        validator.validate(notification);

        String message =
                templateRenderer.render(
                        notification.getMessage(),
                        notification.getTemplate());

        SmsNotification finalNotification =
                SmsNotification.builder()
                        .phoneNumber(notification.getPhoneNumber())
                        .message(message)
                        .build();

        return configuration
                .getProvider()
                .send(finalNotification);

    }

}