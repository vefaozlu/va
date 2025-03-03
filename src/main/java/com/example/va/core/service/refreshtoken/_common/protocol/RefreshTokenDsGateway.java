package com.example.va.core.service.refreshtoken._common.protocol;

import com.example.va.core.service.refreshtoken._common.dto.RefreshTokenDto;

public interface RefreshTokenDsGateway {
    RefreshTokenDto create(RefreshTokenDto refreshTokenDto);

    RefreshTokenDto getRefreshTokenByToken(String token);
}
