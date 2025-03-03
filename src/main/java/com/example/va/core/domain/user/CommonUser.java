package com.example.va.core.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonUser implements User {
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private Role role;
}