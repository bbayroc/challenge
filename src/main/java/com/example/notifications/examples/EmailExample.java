package com.example.notifications.examples;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.provider.email.SendGridProvider;

public final class EmailExample {

    public static void main(String[] args) {

        EmailConfiguration config =
                EmailConfiguration.builder()
                        .provider(
                                new SendGridProvider(
                                        SendGridConfiguration.builder()
                                                .apiKey("test")
                                                .build()))
                        .defaultFrom("noreply@test.com")
                        .build();

    }

}