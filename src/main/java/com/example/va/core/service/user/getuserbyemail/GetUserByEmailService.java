package com.example.va.core.service.user.getuserbyemail;

import com.example.va.infrastructure.Command;
import com.example.va.core.service.user._common.dto.UserDTO;
import com.example.va.core.service.user._common.protocol.UserDSGateway;
import com.example.va.presenter._common.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetUserByEmailService implements Command<GetUserByEmailRequest, UserDTO> {

    private final UserDSGateway userDsGateway;

    public GetUserByEmailService(UserDSGateway userDsGateway) {
        this.userDsGateway = userDsGateway;
    }

    @Override
    public ResponseEntity<UserDTO> execute(GetUserByEmailRequest request) {
        boolean existsByEmail = userDsGateway.existsByEmail(request.getEmail());

        if (existsByEmail) {
            return ResponseEntity.ok(userDsGateway.getUserByEmail(request.getEmail()));
        }

        throw new NotFoundException("User not found");
    }
}
