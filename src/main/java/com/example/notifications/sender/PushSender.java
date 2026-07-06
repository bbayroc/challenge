package com.example.notifications.sender;

import com.example.notifications.config.push.PushConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.template.TemplateRenderer;
import com.example.notifications.validation.PushValidator;

public final class PushSender {

    private final PushConfiguration configuration;

    private final PushValidator validator;

    private final TemplateRenderer renderer;

    public PushSender(
            PushConfiguration configuration,
            PushValidator validator) {

        this.configuration = configuration;
        this.validator = validator;
        this.renderer = new TemplateRenderer();
    }

    public NotificationResult send(
            PushNotification notification) {

        validator.validate(notification);

        String title =
                renderer.render(
                        notification.getTitle(),
                        notification.getTemplate());

        String message =
                renderer.render(
                        notification.getMessage(),
                        notification.getTemplate());

        PushNotification finalNotification =
                PushNotification.builder()
                        .deviceToken(notification.getDeviceToken())
                        .title(title)
                        .message(message)
                        .template(notification.getTemplate())
                        .build();

        return configuration
                .getProvider()
                .send(finalNotification);

    }

}