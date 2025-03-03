package com.example.va.core.service.refreshtoken.createrefreshtoken;

import lombok.Data;

import java.util.Date;

@Data
public class CreateRefreshTokenResponse {
    private final String token;

    private final Integer userId;

    private final Date expiresAt;
}
