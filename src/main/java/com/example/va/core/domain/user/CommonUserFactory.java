package com.example.va.core.domain.user;

import org.springframework.stereotype.Component;

@Component
public class CommonUserFactory implements UserFactory {

    @Override
    public User createUser(String name, String email, String phone, String password, Role role) {
        return new CommonUser(name, email, phone, password, role);
    }
}
