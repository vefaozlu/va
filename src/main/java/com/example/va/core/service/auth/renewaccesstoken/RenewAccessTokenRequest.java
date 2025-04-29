package com.example.va.core.service.auth.renewaccesstoken;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record RenewAccessTokenRequest(
        @NotNull(message = "Refresh token cannot be null.") @NotEmpty(message = "Refresh token cannot be empty.") String refreshToken) {
}
