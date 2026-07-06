package com.example.notifications;

import com.example.notifications.core.NotificationManager;
import com.example.notifications.support.TestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationFactoryTest {

    @Test
    void shouldCreateNotificationManager() {

        try (NotificationManager manager =
                     TestSupport.manager()) {

            assertNotNull(manager);

        }

    }

}