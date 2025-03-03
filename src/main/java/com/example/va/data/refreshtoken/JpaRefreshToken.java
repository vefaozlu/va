package com.example.va.data.refreshtoken;

import com.example.va.core.service.refreshtoken._common.dto.RefreshTokenDto;
import com.example.va.core.service.refreshtoken._common.protocol.RefreshTokenDsGateway;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRefreshToken implements RefreshTokenDsGateway {

    private final JpaRefreshTokenRepository repository;

    JpaRefreshToken(JpaRefreshTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public RefreshTokenDto create(RefreshTokenDto refreshTokenDto) {
        RefreshTokenDataMapper data = new RefreshTokenDataMapper(
                refreshTokenDto.getToken(),
                refreshTokenDto.getUserId(),
                refreshTokenDto.getExpiresAt());

        repository.save(data);

        RefreshTokenDto resDto = new RefreshTokenDto(data.getToken(), data.getUserId(), data.getExpiresAt());
        resDto.setId(data.getId());
        return resDto;
    }

    @Override
    public RefreshTokenDto getRefreshTokenByToken(String token) {
        RefreshTokenDataMapper data = repository.findByToken(token);
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto(data.getToken(), data.getUserId(), data.getExpiresAt());
        refreshTokenDto.setId(data.getId());

        return refreshTokenDto;
    }
}