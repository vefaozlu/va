package com.example.va.core.service.user._common.protocol;

import com.example.va.core.service.user._common.dto.UserDTO;
import com.example.va.core.service.user.createuser.CreateUserDsRequest;

import java.util.List;

public interface UserDSGateway {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsById(Integer id);

    boolean createUser(CreateUserDsRequest user);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Integer id);
}
