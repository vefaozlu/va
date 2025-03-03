package com.example.va.core.service.user._common.dto;

import com.example.va.core.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private Role role;
    private Date deletedAt;
}
