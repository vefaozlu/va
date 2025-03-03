package com.example.va.core.domain.refreshtoken;

import java.util.Date;

public interface RefreshTokenFactory {
    RefreshToken create(String token, Integer userId, Date expiresAt);
}
