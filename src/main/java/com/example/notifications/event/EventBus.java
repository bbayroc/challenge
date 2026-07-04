package com.example.notifications.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EventBus {

    private final List<NotificationEventListener> listeners =
            new CopyOnWriteArrayList<>();

    public void register(NotificationEventListener listener) {

        listeners.add(listener);

    }

    public void publish(NotificationEvent event) {

        for (NotificationEventListener listener : listeners) {

            listener.onEvent(event);

        }

    }

}