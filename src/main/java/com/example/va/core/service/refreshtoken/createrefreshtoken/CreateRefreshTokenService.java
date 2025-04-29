package com.example.va.core.service.refreshtoken.createrefreshtoken;

import com.example.va.Command;
import com.example.va.core.domain.refreshtoken.RefreshToken;
import com.example.va.core.domain.refreshtoken.RefreshTokenFactory;
import com.example.va.core.service.refreshtoken._common.dto.RefreshTokenDto;
import com.example.va.core.service.refreshtoken._common.protocol.RefreshTokenDsGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CreateRefreshTokenService implements Command<CreateRefreshTokenRequest, CreateRefreshTokenResponse> {

    private final RefreshTokenFactory refreshTokenFactory;
    private final RefreshTokenDsGateway refreshTokenDsGateway;

    public CreateRefreshTokenService(RefreshTokenFactory refreshTokenFactory,
                                     RefreshTokenDsGateway refreshTokenDsGateway) {
        this.refreshTokenFactory = refreshTokenFactory;
        this.refreshTokenDsGateway = refreshTokenDsGateway;
    }

    @Override
    public ResponseEntity<CreateRefreshTokenResponse> execute(CreateRefreshTokenRequest request) {
        //  !   These are rules so domain have to
        Date expiresAt = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L);
        UUID refreshToken = UUID.randomUUID();

        RefreshToken token = refreshTokenFactory.create(refreshToken.toString(), request.getUserId(), expiresAt);
        RefreshTokenDto responseDto = refreshTokenDsGateway.create(new RefreshTokenDto(
                token.getToken(),
                token.getUserId(),
                token.getExpiresAt()));

        return ResponseEntity.ok(new CreateRefreshTokenResponse(
                responseDto.getToken(),
                responseDto.getUserId(),
                responseDto.getExpiresAt()));
    }
}
