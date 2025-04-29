package com.example.va.presenter.auth;

import com.example.va.Command;
import com.example.va.core.service.auth.renewaccesstoken.RenewAccessTokenResponse;
import com.example.va.core.service.auth.login.LoginRequest;
import com.example.va.core.service.auth.login.LoginResponse;
import com.example.va.core.service.auth.renewaccesstoken.RenewAccessTokenRequest;
import com.example.va.presenter._common.exceptions.AuthenticationException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class AuthController {

    private final Command<RenewAccessTokenRequest, RenewAccessTokenResponse> renewAccessTokenService;
    private final Command<LoginRequest, LoginResponse> loginService;

    public AuthController(Command<RenewAccessTokenRequest, RenewAccessTokenResponse> renewAccessTokenService,
                          Command<LoginRequest, LoginResponse> loginService) {
        this.renewAccessTokenService = renewAccessTokenService;
        this.loginService = loginService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest user, HttpServletResponse response) {
        ResponseEntity<LoginResponse> dataResponse = loginService.execute(user);

        ResponseCookie cookie = ResponseCookie.from("refresh_token", dataResponse.getBody().refreshToken().refreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return dataResponse;
    }

    @GetMapping("/auth/token-refresh")
    public ResponseEntity<RenewAccessTokenResponse> refreshToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            String token = String.valueOf(Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("refresh_token"))
                    .map(Cookie::getValue)
                    .findFirst());

            if (token.startsWith("Optional[") && token.endsWith("]")) {
                token = token.substring(9, token.length() - 1);
            }

            return renewAccessTokenService.execute(new RenewAccessTokenRequest(token));
        }

        //  Change this message!
        throw new AuthenticationException("Illegal request.");
    }
}
