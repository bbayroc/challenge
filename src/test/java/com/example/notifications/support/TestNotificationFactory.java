package com.example.notifications.support;

import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.model.sms.SmsNotification;

public final class TestNotificationFactory {

    private TestNotificationFactory() {
    }

    public static EmailNotification email() {

        return EmailNotification.builder()
                .recipient(TestConstants.EMAIL)
                .subject(TestConstants.SUBJECT)
                .message(TestConstants.MESSAGE)
                .build();

    }

    public static EmailNotification asyncEmail() {

        return EmailNotification.builder()
                .recipient(TestConstants.EMAIL)
                .subject(TestConstants.ASYNC_SUBJECT)
                .message(TestConstants.ASYNC_MESSAGE)
                .build();

    }

    public static EmailNotification failingEmail() {

        return EmailNotification.builder()
                .recipient(TestConstants.EMAIL)
                .subject(TestConstants.FAILURE_SUBJECT)
                .message(TestConstants.FAILURE_MESSAGE)
                .build();

    }

    public static EmailNotification invalidEmail() {

        return EmailNotification.builder()
                .recipient(TestConstants.INVALID_EMAIL)
                .subject(TestConstants.SUBJECT)
                .message(TestConstants.MESSAGE)
                .build();

    }

    public static SmsNotification sms() {

        return SmsNotification.builder()
                .phoneNumber(TestConstants.PHONE)
                .message(TestConstants.MESSAGE)
                .build();

    }

    public static SmsNotification invalidSms() {

        return SmsNotification.builder()
                .phoneNumber(TestConstants.INVALID_PHONE)
                .message(TestConstants.MESSAGE)
                .build();

    }

    public static PushNotification push() {

        return PushNotification.builder()
                .deviceToken(TestConstants.DEVICE_TOKEN)
                .title(TestConstants.PUSH_TITLE)
                .message(TestConstants.MESSAGE)
                .build();

    }

}