package com.example.va.core.domain.user;

public interface User {
    String getFullName();

    String getEmail();

    String getPhone();

    String getPassword();
    
    Role getRole();
}
