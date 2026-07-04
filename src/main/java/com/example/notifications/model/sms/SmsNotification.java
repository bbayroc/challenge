package com.example.notifications.model.sms;

import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationPriority;

import java.util.Map;

public final class SmsNotification extends Notification {

    private final String phoneNumber;
    private final String message;

    public SmsNotification(
            String phoneNumber,
            String message,
            NotificationPriority priority,
            Map<String, String> metadata) {

        super(
                NotificationChannel.SMS,
                priority,
                metadata);

        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

}