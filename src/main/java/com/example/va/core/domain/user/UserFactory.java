package com.example.va.core.domain.user;

public interface UserFactory {
    User createUser(String name, String email, String phone, String password, Role role);
}
