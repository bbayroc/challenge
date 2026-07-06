package com.example.notifications.exception;

public final class ConfigurationException
        extends NotificationException {

    public ConfigurationException(
            String message) {

        super(message);

    }

    public ConfigurationException(
            String message,
            Throwable cause) {

        super(message, cause);

    }

}