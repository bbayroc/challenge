package com.example.notifications.validation;

import com.example.notifications.core.validation.NotificationValidator;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.exception.ConfigurationException;
import java.util.EnumMap;
import java.util.Map;

public final class ValidationRegistry {

    private final Map<
            NotificationChannel,
            NotificationValidator<? extends Notification>> validators =
            new EnumMap<>(NotificationChannel.class);

    public ValidationRegistry register(
            NotificationValidator<? extends Notification> validator) {

        validators.put(
                validator.supports(),
                validator);

        return this;

    }

    @SuppressWarnings("unchecked")
    public <T extends Notification> void validate(
            T notification) {

        NotificationValidator<T> validator =
                (NotificationValidator<T>)
                        validators.get(
                                notification.getChannel());

        if (validator == null) {

            throw new ConfigurationException(
                    "No validator registered for channel: "
                            + notification.getChannel());

        }

        validator.validate(notification);

    }

}