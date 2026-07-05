package com.example.notifications;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.model.sms.SmsNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.sms.TwilioProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChannelRoutingTest {

    @Test
    void shouldRouteEachNotificationToItsChannel() {

        NotificationManager manager =
                NotificationFactory.createManager(
                        EmailConfiguration.builder()
                                .provider(new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("test")
                                                .build()))
                                .defaultFrom("test@test.com")
                                .build(),

                        SmsConfiguration.builder()
                                .provider(new TwilioProvider(
                                        new TwilioConfiguration()))
                                .build(),

                        new ExponentialBackoffRetryPolicy(3),
                        new CircuitBreaker(3,5000),
                        new EventBus()
                );

        NotificationResult emailResult =
                manager.send(
                        EmailNotification.builder()
                                .recipient("a@test.com")
                                .subject("Mail")
                                .message("Hello")
                                .build());

        NotificationResult smsResult =
                manager.send(
                        SmsNotification.builder()
                                .phoneNumber("+51999999999")
                                .message("SMS")
                                .build());

        NotificationResult pushResult =
                manager.send(
                        PushNotification.builder()
                                .deviceToken("device")
                                .title("Push")
                                .message("Hello")
                                .build());

        assertEquals(NotificationStatus.SUCCESS, emailResult.getStatus());
        assertEquals(NotificationStatus.SUCCESS, smsResult.getStatus());
        assertEquals(NotificationStatus.SUCCESS, pushResult.getStatus());

        assertEquals("SendGrid", emailResult.getProvider());
        assertEquals("Twilio", smsResult.getProvider());
        assertEquals("Firebase", pushResult.getProvider());
    }
}