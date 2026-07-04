package com.example.notifications.api;

public interface Validator<T> {

    void validate(T object);

}