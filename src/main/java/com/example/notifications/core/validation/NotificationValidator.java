package com.example.notifications.core.validation;

import com.example.notifications.model.Notification;

public interface NotificationValidator<T extends Notification> {

    boolean supports(Notification notification);

    void validate(T notification);

}