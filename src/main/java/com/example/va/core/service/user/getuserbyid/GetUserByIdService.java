package com.example.va.core.service.user.getuserbyid;

import com.example.va.infrastructure.Command;
import com.example.va.core.service.user._common.dto.UserDTO;
import com.example.va.core.service.user._common.protocol.UserDSGateway;
import com.example.va.presenter._common.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdService implements Command<GetUserByIdRequest, UserDTO> {

    private final UserDSGateway userDsGateway;

    public GetUserByIdService(UserDSGateway userDsGateway) {
        this.userDsGateway = userDsGateway;
    }

    @Override
    public ResponseEntity<UserDTO> execute(GetUserByIdRequest request) {
        boolean existsById = userDsGateway.existsById(request.getUserId());

        if (existsById) {
            return ResponseEntity.ok(userDsGateway.getUserById(request.getUserId()));
        }

        throw new NotFoundException("User not found");
    }
}
