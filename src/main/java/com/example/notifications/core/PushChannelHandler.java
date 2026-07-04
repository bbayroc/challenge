package com.example.notifications.core;

import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.provider.push.PushProvider;

public final class PushChannelHandler
        implements ChannelHandler<PushNotification> {

    private final PushProvider provider;

    public PushChannelHandler(
            PushProvider provider) {

        this.provider = provider;

    }

    @Override
    public NotificationChannel supports() {

        return NotificationChannel.PUSH;

    }

    @Override
    public NotificationResult send(
            PushNotification notification) {

        return provider.send(notification);

    }

}