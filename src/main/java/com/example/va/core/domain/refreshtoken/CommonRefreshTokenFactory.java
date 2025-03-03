package com.example.va.core.domain.refreshtoken;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommonRefreshTokenFactory implements RefreshTokenFactory {

    @Override
    public RefreshToken create(String token, Integer userId, Date expiresAt) {
        return new CommonRefreshToken(token, userId, expiresAt);
    }
}
