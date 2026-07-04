package com.example.notifications.core;

import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationResult;

public interface ChannelHandler<T extends Notification> {

    NotificationChannel supports();

    NotificationResult send(T notification);

}