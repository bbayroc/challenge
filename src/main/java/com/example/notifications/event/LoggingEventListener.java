package com.example.notifications.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggingEventListener
        implements NotificationEventListener {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    LoggingEventListener.class);

    @Override
    public void onEvent(
            NotificationEvent event) {

        LOGGER.info(
                """
                Notification Event

                Type      : {}
                Timestamp : {}
                Provider  : {}
                Status    : {}
                """,
                event.getType(),
                event.getTimestamp(),
                event.getResult() != null
                        ? event.getResult().getProvider()
                        : "N/A",
                event.getResult() != null
                        ? event.getResult().getStatus()
                        : "N/A"
        );

    }

}