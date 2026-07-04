package com.example.notifications.sender;

import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.sms.SmsNotification;
import com.example.notifications.template.SimpleTemplateEngine;
import com.example.notifications.template.TemplateEngine;
import com.example.notifications.validation.SmsValidator;

public final class SmsSender {

    private final SmsConfiguration configuration;
    private final SmsValidator validator;

    public SmsSender(
            SmsConfiguration configuration,
            SmsValidator validator) {

        this.configuration = configuration;
        this.validator = validator;

    }

    public NotificationResult send(SmsNotification notification) {
        validator.validate(notification);
        String message = notification.getMessage();
        if (notification.getTemplate() != null) {
            TemplateEngine engine = new SimpleTemplateEngine();
            message = engine.render(notification.getTemplate());
        }
        SmsNotification finalNotification = SmsNotification.builder().phoneNumber(notification.getPhoneNumber()).message(message).build();
        return configuration.getProvider().send(finalNotification);
    }

}