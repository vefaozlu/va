package com.example.va.data.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserDataMapper, Integer> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    UserDataMapper findByEmail(String email);
}
