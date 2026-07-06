package com.example.notifications.event;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventBusAsyncTest {

    @Test
    void shouldPublishEventsAsynchronously()
            throws InterruptedException {

        EventBus eventBus =
                new EventBus(true);

        AtomicInteger receivedEvents =
                new AtomicInteger();

        CountDownLatch CountDownLatch =
                new CountDownLatch(1);

        eventBus.register(event -> {

            receivedEvents.incrementAndGet();

            CountDownLatch.countDown();

        });

        eventBus.publish(

                new NotificationEvent(

                        NotificationEventType.SENT,

                        null,

                        Instant.now()

                )

        );

        assertTrue(

                CountDownLatch.await(
                        2,
                        TimeUnit.SECONDS)

        );

        assertEquals(

                1,

                receivedEvents.get()

        );

        eventBus.shutdown();

    }

}