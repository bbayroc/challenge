package com.example.notifications.sender;

import com.example.notifications.model.NotificationResult;
import com.example.notifications.support.TestAssertions;
import com.example.notifications.support.TestConfigurationFactory;
import com.example.notifications.support.TestConstants;
import com.example.notifications.support.TestNotificationFactory;
import com.example.notifications.validation.EmailValidator;
import org.junit.jupiter.api.Test;

class EmailSenderTest {

    @Test
    void shouldSendEmailSuccessfully() {

        EmailSender sender =
                new EmailSender(
                        TestConfigurationFactory.email(),
                        new EmailValidator());

        NotificationResult result =
                sender.send(
                        TestNotificationFactory.email());

        TestAssertions.assertSuccessful(
                result,
                TestConstants.EMAIL_PROVIDER);

    }

}