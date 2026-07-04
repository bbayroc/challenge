package com.example.notifications;

import com.example.notifications.template.NotificationTemplate;
import com.example.notifications.template.SimpleTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TemplateEngineTest {

    @Test
    void shouldRenderTemplateCorrectly() {

        SimpleTemplateEngine engine =
                new SimpleTemplateEngine();

        NotificationTemplate template =
                new NotificationTemplate(
                        "Hello {{name}}, order {{orderId}} ready",
                        Map.of(
                                "name", "John",
                                "orderId", "123"
                        )
                );

        String result = engine.render(template);

        assertEquals(
                "Hello John, order 123 ready",
                result
        );

    }

}