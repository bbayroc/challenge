package com.example.notifications.examples;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.FixedRetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.factory.NotificationFactory;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.sms.TwilioProvider;
import com.example.notifications.template.NotificationTemplate;

import java.util.Map;

public final class TemplateExample {

    public static void main(String[] args) {

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
                        .provider(new TwilioProvider(new TwilioConfiguration()))
                        .build();

        try (NotificationManager manager =
                     NotificationFactory.createManager(
                             emailConfig,
                             smsConfig,
                             new FixedRetryPolicy(3,500),
                             new CircuitBreaker(3,5000),
                             new EventBus())) {

            NotificationTemplate template =
                    new NotificationTemplate(
                            "Hello {{name}}, your order {{order}} is ready.",
                            Map.of(
                                    "name","John",
                                    "order","12345"));

            NotificationResult result =
                    manager.send(
                            EmailNotification.builder()
                                    .recipient("john@example.com")
                                    .subject("Order")
                                    .template(template)
                                    .build());

            System.out.println(result.getStatus());

        }

    }

}