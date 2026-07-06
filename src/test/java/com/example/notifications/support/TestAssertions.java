package com.example.notifications.support;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.model.NotificationStatus;

import static org.junit.jupiter.api.Assertions.*;

public final class TestAssertions {

    private TestAssertions() {
    }

    public static void assertSuccessful(
            NotificationResult result) {

        assertAll(

                () -> assertNotNull(result),

                () -> assertEquals(
                        NotificationStatus.SUCCESS,
                        result.getStatus()),

                () -> assertNotNull(
                        result.getProvider()),

                () -> assertNotNull(
                        result.getTimestamp())

        );

    }

    public static void assertSuccessful(
            NotificationResult result,
            String provider) {

        assertSuccessful(result);

        assertEquals(
                provider,
                result.getProvider());

    }

    public static void assertFailed(
            NotificationResult result) {

        assertAll(

                () -> assertNotNull(result),

                () -> assertEquals(
                        NotificationStatus.FAILED,
                        result.getStatus()),

                () -> assertNotNull(
                        result.getProvider()),

                () -> assertNotNull(
                        result.getTimestamp()),

                () -> assertNotNull(
                        result.getErrorMessage())

        );

    }

    public static void assertFailed(
            NotificationResult result,
            String provider) {

        assertFailed(result);

        assertEquals(
                provider,
                result.getProvider());

    }

}