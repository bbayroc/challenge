package com.example.notifications.factory;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.config.sms.SmsConfiguration;
import com.example.notifications.core.*;
import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.execution.ExecutionContext;
import com.example.notifications.core.retry.RetryPolicy;
import com.example.notifications.event.EventBus;
import com.example.notifications.provider.push.FirebasePushProvider;
import com.example.notifications.provider.push.PushProvider;
import com.example.notifications.sender.EmailSender;
import com.example.notifications.sender.SmsSender;
import com.example.notifications.validation.EmailValidator;
import com.example.notifications.validation.SmsValidator;

import java.util.List;

public final class NotificationFactory {

    private NotificationFactory() {
    }

    public static NotificationManager createManager(
            EmailConfiguration emailConfig,
            SmsConfiguration smsConfig,
            RetryPolicy retryPolicy,
            CircuitBreaker circuitBreaker) {

        EmailSender emailSender =
                new EmailSender(emailConfig, new EmailValidator());

        SmsSender smsSender =
                new SmsSender(smsConfig, new SmsValidator());

        PushProvider pushProvider =
                new FirebasePushProvider();

        List<ChannelHandler<?>> handlers =
                List.of(
                        new EmailChannelHandler(emailSender),
                        new SmsChannelHandler(smsSender),
                        new PushChannelHandler(pushProvider)
                );

        ChannelResolver resolver =
                new ChannelResolver(handlers);

        ExecutionContext context =
                new ExecutionContext(retryPolicy, circuitBreaker);

        EventBus eventBus = new EventBus();

        return new NotificationManager(
                resolver,
                context,
                eventBus
        );
    }

}