package com.example.notifications;

import com.example.notifications.event.EventBus;
import com.example.notifications.event.NotificationEvent;
import com.example.notifications.event.NotificationEventType;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventBusTest {

    @Test
    void shouldReceiveNotificationEvents() {

        EventBus eventBus =
                new EventBus();

        AtomicInteger receivedEvents =
                new AtomicInteger();

        eventBus.register(
                event -> receivedEvents.incrementAndGet());

        eventBus.publish(
                new NotificationEvent(
                        NotificationEventType.SENT,
                        null,
                        Instant.now()));

        assertEquals(
                1,
                receivedEvents.get());

        eventBus.shutdown();

    }

}