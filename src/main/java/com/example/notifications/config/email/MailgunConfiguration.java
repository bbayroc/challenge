package com.example.notifications.config.email;

public final class MailgunConfiguration
        extends EmailProviderConfiguration {

    private final String domain;

    public MailgunConfiguration(
            String apiKey,
            String domain) {

        super(apiKey);

        this.domain = domain;

    }

    public String getDomain() {

        return domain;

    }

}