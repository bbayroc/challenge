package com.example.notifications.core;

import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationChannel;
import com.example.notifications.model.NotificationResult;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ChannelResolver {

    private final Map<NotificationChannel, ChannelHandler<?>> registry;

    public ChannelResolver(
            List<ChannelHandler<?>> handlers) {

        this.registry = handlers.stream()
                .collect(Collectors.toMap(
                        ChannelHandler::supports,
                        Function.identity()
                ));

    }

    @SuppressWarnings("unchecked")
    public NotificationResult resolve(
            Notification notification) {

        ChannelHandler handler =
                registry.get(notification.getChannel());

        if (handler == null) {
            throw new IllegalArgumentException(
                    "No handler for channel: " +
                            notification.getChannel());
        }

        return handler.send(notification);

    }

}