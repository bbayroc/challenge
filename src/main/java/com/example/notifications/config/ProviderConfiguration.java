package com.example.notifications.config;

public abstract class ProviderConfiguration {

    private final String providerName;

    protected ProviderConfiguration(String providerName) {

        this.providerName = providerName;

    }

    public String getProviderName() {

        return providerName;

    }

}