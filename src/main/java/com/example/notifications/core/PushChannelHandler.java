package com.example.notifications.core;

import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.sender.PushSender;

public final class PushChannelHandler
        implements ChannelHandler<PushNotification> {

    private final PushSender sender;

    public PushChannelHandler(
            PushSender sender) {

        this.sender = sender;

    }

    @Override
    public NotificationChannel supports() {

        return NotificationChannel.PUSH;

    }

    @Override
    public NotificationResult send(
            PushNotification notification) {

        return sender.send(notification);

    }

}