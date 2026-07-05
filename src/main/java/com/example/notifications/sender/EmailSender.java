package com.example.notifications.sender;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.template.SimpleTemplateEngine;
import com.example.notifications.template.TemplateEngine;
import com.example.notifications.template.TemplateRenderer;
import com.example.notifications.validation.EmailValidator;

public final class EmailSender {

    private final EmailConfiguration configuration;

    private final EmailValidator validator;

    private final TemplateRenderer templateRenderer;

    public EmailSender(
            EmailConfiguration configuration,
            EmailValidator validator) {

        this.configuration = configuration;
        this.validator = validator;
        this.templateRenderer =
                new TemplateRenderer();

    }

    public NotificationResult send(
            EmailNotification notification) {

        validator.validate(notification);

        String message =
                templateRenderer.render(
                        notification.getMessage(),
                        notification.getTemplate());

        EmailNotification finalNotification =
                EmailNotification.builder()
                        .recipient(notification.getRecipient())
                        .subject(notification.getSubject())
                        .message(message)
                        .build();

        return configuration
                .getProvider()
                .send(finalNotification);

    }

}