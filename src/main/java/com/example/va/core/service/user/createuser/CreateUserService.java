package com.example.va.core.service.user.createuser;

import com.example.va.Command;
import com.example.va.core.domain.user.User;
import com.example.va.core.domain.user.UserFactory;
import com.example.va.core.service.user._common.protocol.UserDSGateway;
import com.example.va.presenter._common.exceptions.DuplicateValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;

@Service
public class CreateUserService implements Command<CreateUserRequest, CreateUserResponse> {

    private final PasswordEncoder encoder;
    private final UserFactory userFactory;
    private final UserDSGateway userDsGateway;

    public CreateUserService(PasswordEncoder encoder, UserFactory userFactory, UserDSGateway userDsGateway) {
        this.encoder = encoder;
        this.userFactory = userFactory;
        this.userDsGateway = userDsGateway;
    }

    @Override
    public ResponseEntity<CreateUserResponse> execute(CreateUserRequest request) {
        User user = userFactory.createUser(
            request.getFullName(), 
            request.getEmail(), 
            request.getPhone(), 
            request.getPassword(),
            request.getRole()
        );

        if (userDsGateway.existsByEmail(user.getEmail()) || userDsGateway.existsByPhone(user.getPhone())) {
            throw new DuplicateValueException("Email or Phone is already in use");
        }

        boolean data = userDsGateway.create(new CreateUserDsRequest(
            user.getFullName(), 
            user.getEmail(), 
            user.getPhone(), 
            encoder.encode(user.getPassword()),
            user.getRole()
        ));
        
        if (data) {
            return ResponseEntity.ok(new CreateUserResponse(true));
        }
        throw new DateTimeException("Something went wrong.");
    }
}
