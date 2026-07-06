package com.example.notifications.support;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.email.MailgunConfiguration;
import com.example.notifications.config.email.SendGridConfiguration;
import com.example.notifications.config.push.FirebaseConfiguration;
import com.example.notifications.config.push.PushConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.config.sms.TwilioConfiguration;
import com.example.notifications.provider.FailingEmailProvider;
import com.example.notifications.provider.email.MailgunProvider;
import com.example.notifications.provider.email.SendGridProvider;
import com.example.notifications.provider.push.FirebasePushProvider;
import com.example.notifications.provider.sms.TwilioProvider;

import java.time.Duration;

public final class TestConfigurationFactory {

    private TestConfigurationFactory() {
    }

    public static EmailConfiguration email() {

        return EmailConfiguration.builder()
                .provider(
                        new SendGridProvider(
                                SendGridConfiguration.builder()
                                        .apiKey(TestConstants.API_KEY)
                                        .build()))
                .defaultFrom(TestConstants.DEFAULT_FROM)
                .build();

    }

    public static EmailConfiguration mailgun() {

        return EmailConfiguration.builder()
                .provider(
                        new MailgunProvider(
                                MailgunConfiguration.builder()
                                        .apiKey(TestConstants.API_KEY)
                                        .build()))
                .defaultFrom(TestConstants.DEFAULT_FROM)
                .build();

    }

    public static EmailConfiguration failingEmail() {

        return EmailConfiguration.builder()
                .provider(
                        new FailingEmailProvider())
                .defaultFrom(
                        TestConstants.DEFAULT_FROM)
                .build();

    }

    public static EmailConfiguration emailWithTimeout(
            Duration timeout) {

        return EmailConfiguration.builder()
                .provider(
                        new SendGridProvider(
                                SendGridConfiguration.builder()
                                        .apiKey(TestConstants.API_KEY)
                                        .build()))
                .defaultFrom(TestConstants.DEFAULT_FROM)
                .timeout(timeout)
                .build();

    }

    public static SmsConfiguration sms() {

        return SmsConfiguration.builder()
                .provider(
                        new TwilioProvider(
                                new TwilioConfiguration()))
                .build();

    }

    public static SmsConfiguration smsWithTimeout(
            Duration timeout) {

        return SmsConfiguration.builder()
                .provider(
                        new TwilioProvider(
                                new TwilioConfiguration()))
                .timeout(timeout)
                .build();

    }

    public static PushConfiguration push() {

        return PushConfiguration.builder()
                .provider(
                        new FirebasePushProvider(
                                FirebaseConfiguration.builder()
                                        .projectId(TestConstants.PROJECT_ID)
                                        .build()))
                .build();

    }

    public static PushConfiguration push(
            String projectId) {

        return PushConfiguration.builder()
                .provider(
                        new FirebasePushProvider(
                                FirebaseConfiguration.builder()
                                        .projectId(projectId)
                                        .build()))
                .build();

    }

}