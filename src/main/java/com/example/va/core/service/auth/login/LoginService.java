package com.example.va.core.service.auth.login;

import com.example.va.infrastructure.Command;
import com.example.va.infrastructure.Mediator;
import com.example.va.core.service.refreshtoken.createrefreshtoken.CreateRefreshTokenRequest;
import com.example.va.core.service.refreshtoken.createrefreshtoken.CreateRefreshTokenResponse;
import com.example.va.core.service.user._common.dto.UserDTO;
import com.example.va.core.service.user.getuserbyemail.GetUserByEmailRequest;
import com.example.va.security.jwt.JwtUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements Command<LoginRequest, LoginResponse> {

    private final Mediator mediator;
    private final AuthenticationManager manager;

    public LoginService(@Lazy Mediator mediator, AuthenticationManager manager) {
        this.mediator = mediator;
        this.manager = manager;
    }

    public ResponseEntity<LoginResponse> execute(LoginRequest request) {
        ResponseEntity<UserDTO> getUserByEmailResponse = mediator.executeCommand(new GetUserByEmailRequest(request.getEmail()));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        );
        Authentication authentication = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = JwtUtil.generateToken((User) authentication.getPrincipal());

        ResponseEntity<CreateRefreshTokenResponse> createRefreshTokenResponse = mediator.executeCommand(
                new CreateRefreshTokenRequest(getUserByEmailResponse.getBody().getId()));

        return ResponseEntity.ok(new LoginResponse(
                new LoginResponse.LoginAccessTokenObj(jwtToken, 300),
                new LoginResponse.LoginRefreshTokenObj(createRefreshTokenResponse.getBody().getToken(), 604800)
        ));
    }
}
