package com.example.notifications.provider.email;

public abstract class AbstractEmailProvider implements EmailProvider {

    @Override
    public String getProviderName() {
        return getClass().getSimpleName();
    }

}