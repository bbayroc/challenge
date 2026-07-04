package com.example.notifications.validation;

import com.example.notifications.exception.ValidationException;

public final class ValidationSupport {

    private ValidationSupport() {
    }

    public static void notBlank(
            String value,
            String field) {

        if (value == null ||
                value.isBlank()) {

            throw new ValidationException(
                    field + " is required");

        }

    }

}