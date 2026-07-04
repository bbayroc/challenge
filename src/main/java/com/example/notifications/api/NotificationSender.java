package com.example.notifications.api;

import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;

public interface NotificationSender<T extends Notification> {

    NotificationResult send(T notification);

}