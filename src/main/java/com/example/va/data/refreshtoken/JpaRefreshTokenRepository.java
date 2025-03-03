package com.example.va.data.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenDataMapper, Integer> {
    RefreshTokenDataMapper findByToken(String token);
}
