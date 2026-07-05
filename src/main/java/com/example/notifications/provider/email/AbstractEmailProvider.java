package com.example.notifications.provider.email;

public abstract class AbstractEmailProvider
        implements EmailProvider {

    private final String providerName;

    protected AbstractEmailProvider(
            String providerName) {

        this.providerName = providerName;

    }

    @Override
    public String getProviderName() {

        return providerName;

    }

}