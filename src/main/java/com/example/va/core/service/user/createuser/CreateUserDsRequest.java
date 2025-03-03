package com.example.va.core.service.user.createuser;

import com.example.va.core.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserDsRequest {

    private String fullName;

    private String email;

    private String phone;

    private String password;

    private Role role;
}
