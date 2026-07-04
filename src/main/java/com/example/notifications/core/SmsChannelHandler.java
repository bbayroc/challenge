package com.example.notifications.core;

import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.sms.SmsNotification;
import com.example.notifications.sender.SmsSender;

public final class SmsChannelHandler
        implements ChannelHandler<SmsNotification> {

    private final SmsSender sender;

    public SmsChannelHandler(
            SmsSender sender) {

        this.sender = sender;

    }

    @Override
    public NotificationChannel supports() {

        return NotificationChannel.SMS;

    }

    @Override
    public NotificationResult send(
            SmsNotification notification) {

        return sender.send(notification);

    }

}