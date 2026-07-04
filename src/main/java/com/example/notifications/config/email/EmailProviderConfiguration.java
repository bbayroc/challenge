package com.example.notifications.config.email;

import com.example.notifications.config.ProviderConfiguration;

public abstract class EmailProviderConfiguration
        implements ProviderConfiguration {

    private final String apiKey;

    protected EmailProviderConfiguration(String apiKey) {

        this.apiKey = apiKey;

    }

    public String getApiKey() {

        return apiKey;

    }

}