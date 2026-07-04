package com.example.notifications.provider.sms;

public abstract class AbstractSmsProvider implements SmsProvider {

    @Override
    public String getProviderName() {
        return getClass().getSimpleName();
    }

}