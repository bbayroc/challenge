package com.example.notifications.event;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventBusTest {

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

        assertEquals(
                1,
                counter.get());

        bus.shutdown();

    }

}