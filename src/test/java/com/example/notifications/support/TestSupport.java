package com.example.notifications.support;

import com.example.notifications.core.NotificationManager;
import com.example.notifications.event.EventBus;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.model.sms.SmsNotification;

public final class TestSupport {

    private TestSupport() {
    }

    public static NotificationManager manager() {

        return TestManagerFactory.create();

    }

    public static NotificationManager asyncManager() {

        return TestManagerFactory.createAsync();

    }

    public static NotificationManager failingManager() {

        return TestManagerFactory.createWithFailingEmailProvider();

    }

    public static NotificationManager manager(
            EventBus eventBus) {

        return TestManagerFactory.createWithEventBus(
                eventBus);

    }

    public static EmailNotification email() {

        return TestNotificationFactory.email();

    }

    public static EmailNotification invalidEmail() {

        return TestNotificationFactory.invalidEmail();

    }

    public static EmailNotification failingEmail() {

        return TestNotificationFactory.failingEmail();

    }

    public static EmailNotification asyncEmail() {

        return TestNotificationFactory.asyncEmail();

    }

    public static SmsNotification sms() {

        return TestNotificationFactory.sms();

    }

    public static SmsNotification invalidSms() {

        return TestNotificationFactory.invalidSms();

    }

    public static PushNotification push() {

        return TestNotificationFactory.push();

    }

}