package com.example.va.core.service.refreshtoken._common.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
public class RefreshTokenDto {
    private Integer id;
    private final String token;
    private final Integer userId;
    private final Date expiresAt;
}
