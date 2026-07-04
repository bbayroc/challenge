package com.example.notifications.template;

public final class SimpleTemplateEngine
        implements TemplateEngine {

    @Override
    public String render(NotificationTemplate template) {

        String result = template.getTemplate();

        for (var entry : template.getVariables().entrySet()) {

            result = result.replace(
                    "{{" + entry.getKey() + "}}",
                    entry.getValue()
            );

        }

        return result;

    }

}