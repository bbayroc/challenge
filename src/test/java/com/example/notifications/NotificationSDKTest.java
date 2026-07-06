package com.example.notifications;

import com.example.notifications.core.circuit.CircuitBreaker;
import com.example.notifications.core.retry.ExponentialBackoffRetryPolicy;
import com.example.notifications.core.sdk.NotificationSDK;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.support.TestAssertions;
import com.example.notifications.support.TestConfigurationFactory;
import com.example.notifications.support.TestConstants;
import com.example.notifications.support.TestNotificationFactory;
import org.junit.jupiter.api.Test;

class NotificationSDKTest {

    @Test
    void shouldSendNotificationUsingSdk() {

        try (NotificationSDK sdk =
                     NotificationSDK.builder()
                             .email(
                                     TestConfigurationFactory.email())
                             .sms(
                                     TestConfigurationFactory.sms())
                             .push(
                                     TestConfigurationFactory.push())
                             .retryPolicy(
                                     new ExponentialBackoffRetryPolicy(3))
                             .circuitBreaker(
                                     new CircuitBreaker(3, 5000))
                             .build()) {

            NotificationResult result =
                    sdk.send(
                            TestNotificationFactory.email());

            TestAssertions.assertSuccessful(
                    result,
                    TestConstants.EMAIL_PROVIDER);

        }

    }

}