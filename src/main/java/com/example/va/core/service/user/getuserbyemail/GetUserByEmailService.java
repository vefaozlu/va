package com.example.va.core.service.user.getuserbyemail;

import com.example.va.Command;
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
            UserDTO user = userDsGateway.getByEmail(request.getEmail());
            return ResponseEntity.ok(user);
        }

        throw new NotFoundException("User not found");
    }
}
