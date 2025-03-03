package com.example.va.presenter.auth;

import com.example.va.RoofCalculation;
import com.example.va.core.service.auth.renewaccesstoken.RenewAccessTokenResponse;
import com.example.va.infrastructure.Mediator;
import com.example.va.core.service.auth.login.LoginRequest;
import com.example.va.core.service.auth.login.LoginResponse;
import com.example.va.core.service.auth.renewaccesstoken.RenewAccessTokenRequest;
import com.example.va.presenter._common.exceptions.AuthenticationException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class AuthController {

    private final Mediator mediator;

    public AuthController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest user, HttpServletResponse response) {
        ResponseEntity<LoginResponse> dataResponse = mediator.executeCommand(user);

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

            return mediator.executeCommand(new RenewAccessTokenRequest(token));
        }

        //  Change this message!
        throw new AuthenticationException("Illegal request.");
    }
}
