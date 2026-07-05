package com.example.notifications.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationMetricsTest {

    @Test
    void shouldCalculateMetricsCorrectly() {

        NotificationMetrics metrics =
                new NotificationMetrics();

        metrics.incrementSent();

        metrics.incrementSent();

        metrics.incrementFailed();

        assertEquals(
                2,
                metrics.getSent());

        assertEquals(
                1,
                metrics.getFailed());

        assertEquals(
                3,
                metrics.getTotal());

        assertEquals(
                NotificationEventType.FAILED,
                metrics.getLastEvent());

        assertEquals(
                66.666,
                metrics.getSuccessRate(),
                0.01);

    }

}