package com.example.notifications.factory;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.push.FirebaseConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.*;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.RetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.provider.push.FirebasePushProvider;
import com.example.notifications.sender.EmailSender;
import com.example.notifications.sender.SmsSender;
import com.example.notifications.validation.EmailValidator;
import com.example.notifications.validation.SmsValidator;
import com.example.notifications.config.push.PushConfiguration;
import com.example.notifications.sender.PushSender;
import com.example.notifications.validation.PushValidator;

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

        PushConfiguration pushConfiguration =
                PushConfiguration.builder()
                        .provider(
                                new FirebasePushProvider(
                                        FirebaseConfiguration.builder()
                                                .projectId("demo-project")
                                                .credentials("demo-credentials")
                                                .build()))
                        .build();

        PushSender pushSender =
                new PushSender(
                        pushConfiguration,
                        new PushValidator());

        return NotificationManager.builder()
                .handler(
                        new EmailChannelHandler(emailSender))
                .handler(
                        new SmsChannelHandler(smsSender))
                .handler(
                        new PushChannelHandler(
                                pushSender))
                .retryPolicy(retryPolicy)
                .circuitBreaker(circuitBreaker)
                .eventBus(eventBus)
                .build();
    }

}