package com.example.notifications.provider.sms;

public abstract class AbstractSmsProvider
        implements SmsProvider {

    private final String providerName;

    protected AbstractSmsProvider(
            String providerName) {

        this.providerName = providerName;

    }

    @Override
    public String getProviderName() {

        return providerName;

    }

}