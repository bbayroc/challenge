package com.example.notifications.sender;

import com.example.notifications.config.email.EmailConfiguration;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.email.EmailNotification;
import com.example.notifications.provider.email.EmailProvider;
import com.example.notifications.validation.EmailValidator;

public final class EmailSender
        extends AbstractSender<EmailNotification> {

    private final EmailProvider provider;

    public EmailSender(
            EmailConfiguration configuration,
            EmailValidator validator) {

        super(validator);
        this.provider = configuration.getProvider();

    }

    @Override
    protected NotificationResult doSend(
            EmailNotification notification) {

        return provider.send(notification);

    }

}