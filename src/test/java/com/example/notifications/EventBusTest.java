package com.example.notifications;

import com.example.notifications.event.EventBus;
import com.example.notifications.event.NotificationEvent;
import com.example.notifications.event.NotificationEventType;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventBusTest {

    @Test
    void shouldReceiveNotificationEvents() {

        EventBus bus = new EventBus();

        AtomicInteger counter = new AtomicInteger();

        bus.register(event -> counter.incrementAndGet());

        bus.publish(
                new NotificationEvent(
                        NotificationEventType.SENT,
                        null,
                        Instant.now()
                )
        );

        assertEquals(1, counter.get());

    }

}
