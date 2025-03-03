package com.example.va.infrastructure;

public interface CommandRegistry {
    <I, O> Command<I, O> getCommand(I input);
}
