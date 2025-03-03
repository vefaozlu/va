package com.example.va.core.domain.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CommonRefreshToken implements RefreshToken {
    String token;
    Integer userId;
    Date expiresAt;

    @Override
    public boolean isExpired() {
        return new Date().after(expiresAt);
    }
}
