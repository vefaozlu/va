package com.example.va.core.service.refreshtoken._common.dto;

import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
public class RefreshTokenDto {
    private Integer id;
    private final String token;
    private final Integer userId;
    private final Date expiresAt;
}
