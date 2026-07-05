package com.example.notifications.event;

public final class NotificationMetrics {

    private int sent;

    private int failed;

    private NotificationEventType lastEvent;

    public void incrementSent() {

        sent++;

        lastEvent = NotificationEventType.SENT;

    }

    public void incrementFailed() {

        failed++;

        lastEvent = NotificationEventType.FAILED;

    }

    public int getSent() {

        return sent;

    }

    public int getFailed() {

        return failed;

    }

    public int getTotal() {

        return sent + failed;

    }

    public double getSuccessRate() {

        if (getTotal() == 0) {

            return 0;

        }

        return (sent * 100.0) / getTotal();

    }

    public NotificationEventType getLastEvent() {

        return lastEvent;

    }

}