package com.example.notifications.provider.sms;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.sms.SmsNotification;

public interface SmsProvider {

    NotificationResult send(SmsNotification notification);

    String getProviderName();

}