package com.example.notifications.template;

import java.util.Map;

public final class NotificationTemplate {

    private final String template;

    private final Map<String, String> variables;

    public NotificationTemplate(
            String template,
            Map<String, String> variables) {

        this.template = template;

        this.variables = variables;

    }

    public String getTemplate() {

        return template;

    }

    public Map<String, String> getVariables() {

        return variables;

    }

}