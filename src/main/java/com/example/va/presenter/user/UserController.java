package com.example.va.presenter.user;

import com.example.va.Command;
import com.example.va.core.service.user.createuser.CreateUserRequest;
import com.example.va.core.service.user.createuser.CreateUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final Command<CreateUserRequest, CreateUserResponse> createUserService;

    public UserController(Command<CreateUserRequest, CreateUserResponse> createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping("/user/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return createUserService.execute(request);
    }

    @GetMapping("/user/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminOnlyEndpoint() {
        return ResponseEntity.ok("This endpoint is only accessible by admins.");
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("This endpoint is accessible by both admins nad users.");
    }
}