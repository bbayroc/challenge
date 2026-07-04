package com.example.notifications.model;

/**
 * Base contract for every notification supported by the library.
 */
public interface Notification {

    NotificationChannel getChannel();

}