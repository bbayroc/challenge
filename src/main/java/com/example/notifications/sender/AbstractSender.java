package com.example.notifications.sender;

import com.example.notifications.api.NotificationSender;
import com.example.notifications.api.Validator;
import com.example.notifications.model.Notification;
import com.example.notifications.model.NotificationResult;

public abstract class AbstractSender<T extends Notification>
        implements NotificationSender<T> {

    protected final Validator<T> validator;

    protected AbstractSender(Validator<T> validator) {
        this.validator = validator;
    }

    protected void validate(T notification) {
        validator.validate(notification);
    }

    @Override
    public NotificationResult send(T notification) {
        validate(notification);
        return doSend(notification);
    }

    protected abstract NotificationResult doSend(T notification);

}