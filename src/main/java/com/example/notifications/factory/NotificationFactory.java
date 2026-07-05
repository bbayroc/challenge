package com.example.notifications.factory;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.*;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.RetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.provider.push.FirebasePushProvider;
import com.example.notifications.provider.push.PushProvider;
import com.example.notifications.sender.EmailSender;
import com.example.notifications.sender.SmsSender;
import com.example.notifications.validation.EmailValidator;
import com.example.notifications.validation.SmsValidator;

public final class NotificationFactory {

    private NotificationFactory() {
    }

    public static NotificationManager createManager(
            EmailConfiguration emailConfig,
            SmsConfiguration smsConfig,
            RetryPolicy retryPolicy,
            CircuitBreaker circuitBreaker,
            EventBus eventBus){

        EmailSender emailSender =
                new EmailSender(emailConfig, new EmailValidator());

        SmsSender smsSender =
                new SmsSender(smsConfig, new SmsValidator());

        PushProvider pushProvider =
                new FirebasePushProvider();

        return NotificationManager.builder()
                .handler(
                        new EmailChannelHandler(emailSender))
                .handler(
                        new SmsChannelHandler(smsSender))
                .handler(
                        new PushChannelHandler(pushProvider))
                .retryPolicy(retryPolicy)
                .circuitBreaker(circuitBreaker)
                .eventBus(eventBus)
                .build();
    }

}