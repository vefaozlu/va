package com.example.va.data.user;

import com.example.va.core.domain.user.Role;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.Date;

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@SQLRestriction("deleted <> true")
@Data
@NoArgsConstructor
public class UserDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    private boolean deleted = Boolean.FALSE;

    UserDataMapper(String fullName, String email, String phone, String password, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }
}