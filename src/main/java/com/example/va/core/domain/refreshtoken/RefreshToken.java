package com.example.va.core.domain.refreshtoken;

import java.util.Date;

public interface RefreshToken {
    String getToken();

    Integer getUserId();

    Date getExpiresAt();

    boolean isExpired();
}
