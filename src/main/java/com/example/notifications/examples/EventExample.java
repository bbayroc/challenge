package com.example.notifications.examples;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.push.FirebaseConfiguration;
import com.example.notifications.config.push.PushConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.core.retry.FixedRetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.event.LoggingEventListener;
import com.example.notifications.event.MetricsEventListener;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.push.FirebasePushProvider;
import com.example.notifications.provider.sms.TwilioProvider;

public final class EventExample {

    public static void main(String[] args) {

        EventBus bus = new EventBus();

        MetricsEventListener metrics =
                new MetricsEventListener();

        bus.register(new LoggingEventListener());
        bus.register(metrics);

        EmailConfiguration emailConfig =
                EmailConfiguration.builder()
                        .provider(
                                new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("demo")
                                                .build()))
                        .defaultFrom("demo@example.com")
                        .build();

        SmsConfiguration smsConfig =
                SmsConfiguration.builder()
                        .provider(
                                new TwilioProvider(
                                        new TwilioConfiguration()))
                        .build();

        PushConfiguration pushConfig =
                PushConfiguration.builder()
                        .provider(
                                new FirebasePushProvider(
                                        FirebaseConfiguration.builder()
                                                .projectId("demo-project")
                                                .build()))
                        .build();

        try (NotificationManager manager =
                     NotificationFactory.createManager(
                             emailConfig,
                             smsConfig,
                             pushConfig,
                             new ExponentialBackoffRetryPolicy(3),
                             new CircuitBreaker(3, 5000),
                             new EventBus())) {

            manager.send(
                    EmailNotification.builder()
                            .recipient("john@example.com")
                            .subject("Events")
                            .message("Testing EventBus")
                            .build());

        }

        System.out.println("Sent         : " + metrics.getMetrics().getSent());
        System.out.println("Failed       : " + metrics.getMetrics().getFailed());
        System.out.println("Success Rate : " + metrics.getMetrics().getSuccessRate()+"%");
        System.out.println("Last Event   : " + metrics.getMetrics().getLastEvent());

    }

}