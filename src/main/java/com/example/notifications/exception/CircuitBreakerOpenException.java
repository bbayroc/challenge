package com.example.notifications.exception;

public final class CircuitBreakerOpenException
        extends NotificationException {

    public CircuitBreakerOpenException(
            String message) {

        super(message);

    }

}