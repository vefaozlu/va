package com.example.va.infrastructure.impl;

import com.example.va.infrastructure.Command;
import com.example.va.infrastructure.CommandRegistry;
import com.example.va.infrastructure.Mediator;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;

@Component
public class CommonMediator implements Mediator {

    private final CommandRegistry commandRegistry;

    public CommonMediator(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Override
    public <I, O> ResponseEntity<O> executeCommand(I input) {
        Command<I, O> command = commandRegistry.getCommand(input);
        if (command == null) {
            throw new IllegalArgumentException("No command found for input type: " + input.getClass());
        }
        return command.execute(input);
    }
}