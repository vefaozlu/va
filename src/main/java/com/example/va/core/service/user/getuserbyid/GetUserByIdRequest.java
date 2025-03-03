package com.example.va.core.service.user.getuserbyid;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetUserByIdRequest {
    @NotNull(message = "User ID cannot be null")
    @NotEmpty(message = "User ID cannot be null")
    private final Integer userId;
}
