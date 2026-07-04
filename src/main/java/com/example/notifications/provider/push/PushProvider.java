package com.example.notifications.provider.push;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.push.PushNotification;

public interface PushProvider {

    NotificationResult send(PushNotification notification);

    String getProviderName();

}