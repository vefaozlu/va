package com.example.va.core.service.auth.renewaccesstoken;

import com.example.va.infrastructure.Command;
import com.example.va.infrastructure.Mediator;
import com.example.va.core.domain.refreshtoken.RefreshToken;
import com.example.va.core.domain.refreshtoken.RefreshTokenFactory;
import com.example.va.core.service.refreshtoken._common.dto.RefreshTokenDto;
import com.example.va.core.service.refreshtoken._common.protocol.RefreshTokenDsGateway;
import com.example.va.core.service.user._common.dto.UserDTO;
import com.example.va.core.service.user.getuserbyid.GetUserByIdRequest;
import com.example.va.presenter._common.exceptions.AuthenticationException;
import com.example.va.security.jwt.JwtUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RenewAccessTokenService implements Command<RenewAccessTokenRequest, RenewAccessTokenResponse> {

    private final Mediator mediator;
    private final RefreshTokenFactory refreshTokenFactory;
    private final RefreshTokenDsGateway refreshTokenDsGateway;

    public RenewAccessTokenService(@Lazy Mediator mediator,
                                   RefreshTokenFactory refreshTokenFactory,
                                   RefreshTokenDsGateway refreshTokenDsGateway) {
        this.refreshTokenFactory = refreshTokenFactory;
        this.refreshTokenDsGateway = refreshTokenDsGateway;
        this.mediator = mediator;
    }

    @Override
    public ResponseEntity<RenewAccessTokenResponse> execute(RenewAccessTokenRequest request) {
        RefreshTokenDto refreshTokenResponse = refreshTokenDsGateway.getRefreshTokenByToken(request.getRefreshToken());

        RefreshToken refreshToken = refreshTokenFactory.create(
                refreshTokenResponse.getToken(),
                refreshTokenResponse.getUserId(),
                refreshTokenResponse.getExpiresAt());

        boolean isExpired = refreshToken.isExpired();

        if (isExpired) {
            throw new AuthenticationException("Token expired");
        }

        ResponseEntity<UserDTO> userResponse = mediator.executeCommand(new GetUserByIdRequest(refreshTokenResponse.getUserId()));

        String jwtToken = JwtUtil.generateToken(userResponse.getBody().getEmail());

        return ResponseEntity.ok(new RenewAccessTokenResponse(
                new RenewAccessTokenResponse.RenewAccessTokenRefreshTokenObj(jwtToken, 300)
        ));
    }
}
