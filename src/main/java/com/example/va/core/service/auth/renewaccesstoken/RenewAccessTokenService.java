package com.example.va.core.service.auth.renewaccesstoken;

import com.example.va.Command;
import com.example.va.core.domain.refreshtoken.RefreshToken;
import com.example.va.core.domain.refreshtoken.RefreshTokenFactory;
import com.example.va.core.service.refreshtoken._common.dto.RefreshTokenDto;
import com.example.va.core.service.refreshtoken._common.protocol.RefreshTokenDsGateway;
import com.example.va.core.service.user._common.dto.UserDTO;
import com.example.va.core.service.user.getuserbyid.GetUserByIdRequest;
import com.example.va.presenter._common.exceptions.AuthenticationException;
import com.example.va.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RenewAccessTokenService implements Command<RenewAccessTokenRequest, RenewAccessTokenResponse> {

    private final RefreshTokenFactory refreshTokenFactory;
    private final RefreshTokenDsGateway refreshTokenDsGateway;
    private final Command<GetUserByIdRequest, UserDTO> getUserByIdService;

    public RenewAccessTokenService(
            RefreshTokenFactory refreshTokenFactory,
            RefreshTokenDsGateway refreshTokenDsGateway,
            Command<GetUserByIdRequest, UserDTO> getUserByIdService
    ) {
        this.refreshTokenFactory = refreshTokenFactory;
        this.refreshTokenDsGateway = refreshTokenDsGateway;
        this.getUserByIdService = getUserByIdService;
    }

    @Override
    public ResponseEntity<RenewAccessTokenResponse> execute(RenewAccessTokenRequest request) {
        RefreshTokenDto refreshTokenResponse = refreshTokenDsGateway.getByToken(request.refreshToken());

        RefreshToken refreshToken = refreshTokenFactory.create(
                refreshTokenResponse.getToken(),
                refreshTokenResponse.getUserId(),
                refreshTokenResponse.getExpiresAt()
        );

        boolean isExpired = refreshToken.isExpired();

        if (isExpired) {
            throw new AuthenticationException("Token expired");
        }

        ResponseEntity<UserDTO> userResponse = getUserByIdService.execute(new GetUserByIdRequest(refreshTokenResponse.getUserId()));

        String jwtToken = JwtUtil.generateToken(userResponse.getBody().getEmail());

        return ResponseEntity.ok(new RenewAccessTokenResponse(
                new RenewAccessTokenResponse.RenewAccessTokenRefreshTokenObj(jwtToken, 300)
        ));
    }
}
