package com.example.notifications.support;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.NotificationManager;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.core.retry.FixedRetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.factory.NotificationFactory;

public final class TestManagerFactory {

    private TestManagerFactory() {
    }

    public static NotificationManager create() {

        return create(
                TestConfigurationFactory.email(),
                TestConfigurationFactory.sms()
        );

    }

    public static NotificationManager create(
            EmailConfiguration emailConfiguration,
            SmsConfiguration smsConfiguration) {

        return NotificationFactory.createManager(

                emailConfiguration,

                smsConfiguration,

                TestConfigurationFactory.push(),

                new FixedRetryPolicy(3, 500),

                new CircuitBreaker(3, 5000),

                new EventBus()

        );

    }

    public static NotificationManager createAsync() {

        return NotificationFactory.createManager(

                TestConfigurationFactory.email(),

                TestConfigurationFactory.sms(),

                TestConfigurationFactory.push(),

                new ExponentialBackoffRetryPolicy(3),

                new CircuitBreaker(3, 5000),

                new EventBus()

        );

    }

    public static NotificationManager createWithEventBus(
            EventBus eventBus) {

        return NotificationFactory.createManager(

                TestConfigurationFactory.email(),

                TestConfigurationFactory.sms(),

                TestConfigurationFactory.push(),

                new FixedRetryPolicy(3, 500),

                new CircuitBreaker(3, 5000),

                eventBus

        );

    }

    public static NotificationManager createWithFailingEmailProvider() {

        return NotificationFactory.createManager(

                TestConfigurationFactory.failingEmail(),

                TestConfigurationFactory.sms(),

                TestConfigurationFactory.push(),

                new ExponentialBackoffRetryPolicy(3),

                new CircuitBreaker(3,5000),

                new EventBus()

        );

    }

}