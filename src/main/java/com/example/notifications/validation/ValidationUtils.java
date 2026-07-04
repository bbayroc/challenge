package com.example.notifications.validation;

import java.util.regex.Pattern;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile(
                    "^\\+?[0-9]{8,15}$");

    public static boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        return EMAIL_PATTERN
                .matcher(email)
                .matches();

    }

    public static boolean isValidPhone(String phone) {

        if (phone == null) {
            return false;
        }

        return PHONE_PATTERN
                .matcher(phone)
                .matches();

    }

}