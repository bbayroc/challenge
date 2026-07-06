package com.example.notifications.core.validation;

import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationChannel;

public interface NotificationValidator<T extends Notification> {

    NotificationChannel supports();

    void validate(T notification);

}