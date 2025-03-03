package com.example.va.core.service.auth.login;

public record LoginResponse(LoginAccessTokenObj accessToken, LoginRefreshTokenObj refreshToken) {
    public record LoginAccessTokenObj(String accessToken, Integer expiresIn) {}

    public record LoginRefreshTokenObj(String refreshToken, Integer expiresIn) {}
}
