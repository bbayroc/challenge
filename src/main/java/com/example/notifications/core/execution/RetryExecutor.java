package com.example.notifications.core.execution;

import com.example.notifications.core.retry.RetryPolicy;
import com.example.notifications.exception.ProviderException;
import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;

import java.util.function.Supplier;

public final class RetryExecutor {

    private final RetryPolicy retryPolicy;

    public RetryExecutor(
            RetryPolicy retryPolicy) {

        this.retryPolicy = retryPolicy;

    }

    public NotificationResult execute(
            Supplier<NotificationResult> action) {

        int attempt = 0;

        while (true) {

            try {

                NotificationResult result =
                        action.get();

                if (result.getStatus()
                        != NotificationStatus.FAILED) {

                    return result;

                }

                ProviderException ex =
                        new ProviderException(
                                result.getErrorMessage());

                if (!retryPolicy.shouldRetry(
                        attempt,
                        ex)) {

                    return result;

                }

                Thread.sleep(
                        retryPolicy.getDelayMillis(
                                attempt));

                attempt++;

            } catch (Exception ex) {

                if (!retryPolicy.shouldRetry(
                        attempt,
                        ex)) {

                    throw new ProviderException(
                            "Notification delivery failed",
                            ex);

                }

                try {

                    Thread.sleep(
                            retryPolicy.getDelayMillis(
                                    attempt));

                } catch (InterruptedException ignored) {

                    Thread.currentThread().interrupt();

                }

                attempt++;

            }

        }

    }

}