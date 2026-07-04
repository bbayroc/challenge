package com.example.notifications.model.push;

import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationPriority;

import java.util.Map;

public final class PushNotification extends Notification {

    private final String deviceToken;
    private final String title;
    private final String message;

    public PushNotification(
            String deviceToken,
            String title,
            String message,
            NotificationPriority priority,
            Map<String, String> metadata) {

        super(
                NotificationChannel.PUSH,
                priority,
                metadata);

        this.deviceToken = deviceToken;
        this.title = title;
        this.message = message;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

}