package com.example.notifications.examples;

import com.example.notifications.config.push.PushConfiguration;
import com.example.notifications.model.push.PushNotification;
import com.example.notifications.sender.PushSender;
import com.example.notifications.validation.PushValidator;
import com.example.notifications.provider.push.OneSignalPushProvider;

public final class PushProviderExample {

    public static void main(String[] args) {

        PushConfiguration configuration =
                PushConfiguration.builder()
                        .provider(
                                new OneSignalPushProvider())
                        .build();

        PushSender sender =
                new PushSender(
                        configuration,
                        new PushValidator());

        var result =
                sender.send(
                        PushNotification.builder()
                                .deviceToken("device-123")
                                .title("Push Example")
                                .message("Hello OneSignal")
                                .build());

        System.out.println(result.getProvider());

    }

}