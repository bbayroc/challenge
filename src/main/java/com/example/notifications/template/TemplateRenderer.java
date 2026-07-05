package com.example.notifications.template;

public final class TemplateRenderer {

    private final TemplateEngine engine =
            new SimpleTemplateEngine();

    public String render(
            String originalMessage,
            NotificationTemplate template) {

        if (template == null) {
            return originalMessage;
        }

        return engine.render(template);

    }

}