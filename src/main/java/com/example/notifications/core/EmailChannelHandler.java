package com.example.notifications.core;

import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.sender.EmailSender;

public final class EmailChannelHandler
        implements ChannelHandler<EmailNotification> {

    private final EmailSender sender;

    public EmailChannelHandler(
            EmailSender sender) {

        this.sender = sender;

    }

    @Override
    public NotificationChannel supports() {

        return NotificationChannel.EMAIL;

    }

    @Override
    public NotificationResult send(
            EmailNotification notification) {

        return sender.send(notification);

    }

}