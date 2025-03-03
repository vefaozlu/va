package com.example.va.core.service.auth.renewaccesstoken;

import java.util.Date;

public record RenewAccessTokenResponse(RenewAccessTokenRefreshTokenObj accessToken) {
    public record RenewAccessTokenRefreshTokenObj(String accessToken, Integer expiresIn){
    }
}

