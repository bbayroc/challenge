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

        EventBus bus =
                new EventBus(true);

        AtomicInteger counter =
                new AtomicInteger();

        CountDownLatch latch =
                new CountDownLatch(1);

        bus.register(event -> {

            counter.incrementAndGet();

            latch.countDown();

        });

        bus.publish(

                new NotificationEvent(

                        NotificationEventType.SENT,

                        null,

                        Instant.now()

                )

        );

        assertTrue(

                latch.await(
                        2,
                        TimeUnit.SECONDS)

        );

        assertEquals(

                1,

                counter.get()

        );

        bus.shutdown();

    }

}