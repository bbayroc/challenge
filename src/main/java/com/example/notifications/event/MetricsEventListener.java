package com.example.notifications.event;

public final class MetricsEventListener
        implements NotificationEventListener {

    private final NotificationMetrics metrics =
            new NotificationMetrics();

    @Override
    public void onEvent(
            NotificationEvent event) {

        switch (event.getType()) {

            case SENT:

                metrics.incrementSent();

                break;

            case FAILED:

                metrics.incrementFailed();

                break;

            default:

                break;

        }

    }

    public NotificationMetrics getMetrics() {

        return metrics;

    }

}