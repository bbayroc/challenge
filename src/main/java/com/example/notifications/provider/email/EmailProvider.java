package com.example.notifications.provider.email;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;

public interface EmailProvider {

    NotificationResult send(EmailNotification notification);

    String getProviderName();

}