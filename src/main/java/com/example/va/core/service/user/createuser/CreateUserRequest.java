package com.example.va.core.service.user.createuser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import com.example.va.core.domain.user.Role;

@AllArgsConstructor
@Getter
@ToString
public class CreateUserRequest {
    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name cannot be empty.")
    private String fullName;

    @NotNull(message = "Email cannot be null.")
    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Email is not valid.")
    private String email;

    private String phone;

    @NotNull(message = "Password cannot be null.")
    @NotEmpty(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password should be 8 chars min.")
    private String password;

    @NotNull(message = "Role cannot be null.")
    private Role role;
}
