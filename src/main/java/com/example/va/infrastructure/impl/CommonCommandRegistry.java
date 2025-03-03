package com.example.va.infrastructure.impl;

import com.example.va.infrastructure.Command;
import com.example.va.infrastructure.CommandRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommonCommandRegistry implements CommandRegistry {
    private final ApplicationContext applicationContext;

    public CommonCommandRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private final Map<Class<?>, Command<?, ?>> registry = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Command> beansOfType = applicationContext.getBeansOfType(Command.class);
        beansOfType.values().forEach(command -> {
            Class<?> inputType = resolveInputType(command);
            registry.put(inputType, command);
        });
    }

    private Class<?> resolveInputType(Command<?, ?> command) {
        return command.getClass().getMethods()[0].getParameterTypes()[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public <I, O> Command<I, O> getCommand(I input) {
        return (Command<I, O>) registry.get(input.getClass());
    }
}
